package com.example.demo.constants;

public class Constants {
    public static final String UPDATE_SUCCESS = "Successfully Updated";

    /* Authorization Constants */
    public static final String OR = " or ";
    public static final String HAS_ROLE_LABORER = "hasRole('LABORER')";
    public static final String HAS_ROLE_EMPLOYER = "hasRole('EMPLOYER')";
    public static final String HAS_ROLE_ADMINISTRATOR = "hasRole('ADMINISTRATOR')";
    public static final String HAS_ROLE_ANONYMOUS = "hasRole('ANONYMOUS')";
    public static final String HAS_ROLE_USER = HAS_ROLE_LABORER  + OR + HAS_ROLE_EMPLOYER + OR + HAS_ROLE_ADMINISTRATOR;
    public static final String HAS_ANY_ROLE = HAS_ROLE_LABORER + OR + HAS_ROLE_ANONYMOUS + OR + HAS_ROLE_EMPLOYER + OR + HAS_ROLE_ADMINISTRATOR;
}