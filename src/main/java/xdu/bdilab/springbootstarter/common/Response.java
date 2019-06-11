package xdu.bdilab.springbootstarter.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cwz
 * @date 2019/05/12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T>{
    private boolean isSucceed;
    private String code;
    private String msg;
    private T data;

    public static <T> Response succeed(String code, String msg, T data){
        return Response.builder().isSucceed(true).code(code).msg(msg).data(data).build();
    }

    public static <T> Response succeed(CodeAndMsg codeAndMsg, T data){
        return succeed(codeAndMsg.getCode(), codeAndMsg.getMsg(), data);
    }

    public static <T> Response succeed(T data){
        return succeed(CodeAndMsg.operation_succeed, data);
    }

    public static <T> Response failed(String code, String msg, T data){
        return Response.builder().isSucceed(false).code(code).msg(msg).data(data).build();
    }

    public static <T> Response failed(CodeAndMsg codeAndMsg){
        return failed(codeAndMsg.getCode(), codeAndMsg.getMsg(), null);
    }
}
