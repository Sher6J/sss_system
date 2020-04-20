package cn.sher6j.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author sher6j
 * @create 2020-04-19-12:07
 */
public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }


    public static void main(String[] args) {
        String password="root";
        String encodePassword = encodePassword(password);
        System.out.println(encodePassword);
        //$2a$10$XSp5TBPbxU5/UhGd/Oc0GOcLJBdmBF97NhGALKtdzZ0igQhBy49ri
    }
}
