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
public class TopException extends RuntimeException{
    private CodeAndMsg response;
}
