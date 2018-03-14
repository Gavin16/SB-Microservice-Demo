package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @Title: SBService
 * @Package: com.demo.aspect
 * @Description:
 * @author: 80002748
 * @date 2018/2/27 17:49
 */

@Aspect
@Component
public class HttpAspect {

    private static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    // 使用within指示器修改切点对应的匹配类型
    // 切点表达式中也可以指定bean
//    @Pointcut("execution(public * com.example.demo.controller.*.*(..)) && within(com.example.demo.*.*) && bean('ocrController')")
    @Pointcut("execution(public * com.example.demo.controller.*.*(..)) && within(com.example.demo.*.*)")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("url={}", request.getRequestURI());

        logger.info("method={}", request.getMethod());

        logger.info("ip={}", request.getRemoteAddr());

        logger.info("class_method={}", joinPoint.getSignature().getDeclaringType() + "." + joinPoint.getSignature().getName());

        logger.info("args={}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {
        logger.info("doAfter method executing");
    }

    @AfterReturning(pointcut = "log()", returning = "object")//打印输出结果
    public void doAfterReturing(Object object) {
        logger.info("response={}", object.toString());
    }

//    @Around("log()") // 前置+后置通知 改为环绕通知
//    public void doAround(ProceedingJoinPoint proceedingJoinPoint,JoinPoint joinPoint){
//        try {
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            HttpServletRequest request = attributes.getRequest();
//
//            logger.info("url={}", request.getRequestURI());
//
//            logger.info("method={}", request.getMethod());
//
//            logger.info("ip={}", request.getRemoteAddr());
//
//            logger.info("class_method={}", joinPoint.getSignature().getDeclaringType() + "." + joinPoint.getSignature().getName());
//
//            logger.info("args={}", joinPoint.getArgs());
//
//            proceedingJoinPoint.proceed();
//
//            logger.info("doAfter method executing");
//        } catch (Throwable e) {
//            logger.error("around advice exception"+e);
//        }
//    }
}
