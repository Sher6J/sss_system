package cn.sher6j.controller;

import cn.sher6j.domain.SysLog;
import cn.sher6j.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author sher6j
 * @create 2020-04-20-13:44
 */
@Component
@Aspect//切面
public class LogAop {

    private Date visitTime; //开始访问时间
    private Class aClass; //访问的类
    private Method method; //访问的方法

    /**
     * RequestContextListener（web.xml中）监听器可以注入request
     */
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;


    /**
     * 前置通知，拦截controller的所有方法
     * 主要是获取开始时间，执行的类时哪一个，执行的是哪一个方法
     * @param jp
     */
    @Before("execution(* cn.sher6j.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date(); //当前时间就是开始访问的时间
        aClass = jp.getTarget().getClass(); //具体访问的类对象
        String methodName = jp.getSignature().getName(); //获取访问的方法的名称
        Object[] args = jp.getArgs(); //获取访问的方法的参数

        //获取具体执行方法的Method对象
        if (args == null || args.length == 0) {
            method = aClass.getMethod(methodName); //只能获取无参数的方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            aClass.getMethod(methodName, classArgs);
        }
    }

    /**
     * 后置通知
     * @param jp
     */
    @After("execution(* cn.sher6j.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime(); //获取访问时长

        String url = "";

        //获取url
        if (aClass != null && method != null && aClass != LogAop.class) {
            //1.获取类上的@RequestMapping的值
            RequestMapping classAnnotation = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //2.获取方法上的@RequestMapping的值
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();

                    url = classValue[0] + methodValue[0];
                }
            }
        }

        //获取访问的IP地址
        String ip = request.getRemoteAddr();

        //获取当前操作的用户
        SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //将日志相关信息封装到SysLog对象中
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);//访问时长
        sysLog.setIp(ip);
        sysLog.setMethod("[类名] " + aClass.getName() +" [方法名] " + method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);//访问时间

        //调用Service完成数据库记录日志操作
        sysLogService.save(sysLog);
    }
}
