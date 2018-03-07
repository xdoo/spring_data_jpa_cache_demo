package com.example.cachedemo;


import lombok.extern.java.Log;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Log
public class SpringDataExecutionLoggingConfiguration {

  @Bean
  public CustomizableTraceInterceptor customizableTraceInterceptor() {

    CustomizableTraceInterceptor customizableTraceInterceptor = new CustomizableTraceInterceptor();
    customizableTraceInterceptor.setUseDynamicLogger(true);
    customizableTraceInterceptor.setEnterMessage("----- Entering $[methodName]($[arguments])");
    customizableTraceInterceptor.setExitMessage("----- Leaving  $[methodName](), returned $[returnValue]");
    log.info("Initialized TraceInterceptor.");
    return customizableTraceInterceptor;
  }

  @Bean
  public Advisor advisor() {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression("execution(public * org.springframework.data.repository.CrudRepository+.*(..))");
    log.info("Initialized Advisor.");
    return new DefaultPointcutAdvisor(pointcut, customizableTraceInterceptor());
  }
}
