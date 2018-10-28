package com.github.dice.constant;

public enum ErrorEnums {
    E_0000("E_0000", "success"),
    E_0001("E_0001", "Illegal parameter"),


    E_5001("E_5001","Login failed"),
    E_5002("E_5002","User is not logged in"),

    ;
    private String code;

    private String msg;

    ErrorEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ErrorEnums getValueOfCode(String code) {
        for (ErrorEnums item : ErrorEnums.values()) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return null;
    }


}
