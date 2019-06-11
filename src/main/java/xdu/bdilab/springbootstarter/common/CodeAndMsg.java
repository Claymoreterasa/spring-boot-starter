package xdu.bdilab.springbootstarter.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CodeAndMsg {
    operation_succeed("0200", "操作成功"),
    login_succeed("0001", "登录成功"),
    logout_succeed("0002", "登出成功"),

    authentication_failed("0002", "认证失败"),
    authorization_failed("0003", "无访问权限"),
    user_not_found("0004", "无此用户");

    @Getter
    private String code;

    @Getter
    private String msg;
}
