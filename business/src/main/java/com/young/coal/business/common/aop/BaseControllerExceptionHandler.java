package com.young.coal.business.common.aop;



import com.young.coal.business.common.exception.CoalException;
import com.young.coal.business.model.SuccessTip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 */
@ControllerAdvice
public class BaseControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截业务异常
     *
     * @author fengshuonan
     */
    @ExceptionHandler(CoalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public SuccessTip notFount(CoalException e) {
        log.error("业务异常:", e);
        return new SuccessTip(500, e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     *
     * @author fengshuonan
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public SuccessTip notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return new SuccessTip(500, e.getMessage());
    }

}
