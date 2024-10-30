package az.ingress.aop;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LoggingAspect {
    @Pointcut("within(@az.ingress.aop.annotation.Log *)")
    public void loggerPointCut() {
    }

    @SneakyThrows
    @Around(value = "loggerPointCut()")
    public Object loggerAspect(ProceedingJoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        var method = signature.getMethod();

        log.info("ActionLog." + method.getName() + ".start");
        var response = jp.proceed();
        log.info("ActionLog." + method.getName() + ".success");
        return response;
    }

}