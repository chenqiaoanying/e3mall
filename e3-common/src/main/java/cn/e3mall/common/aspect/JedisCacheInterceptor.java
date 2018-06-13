package cn.e3mall.common.aspect;

import cn.e3mall.common.jedis.JedisClient;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

public class JedisCacheInterceptor implements MethodInterceptor {

    private JedisClient jedisClient;

    public JedisClient getJedisClient() {
        return jedisClient;
    }

    @Autowired
    public void setJedisClient(JedisClient jedisClient) {
        this.jedisClient = jedisClient;
    }

    /*@Around(value="@annotation(JedisCache)")
    public Object doCacheEvict(ProceedingJoinPoint joinPoint) throws Throwable {

        *//*获取方法名*//*
        String methodName = joinPoint.getSignature().getName();
        *//*获取方法的参数*//*
        String args = Arrays.toString(joinPoint.getArgs());

        ((MethodSignature)joinPoint.getSignature()).getMethod().
        Object result = joinPoint.proceed();

        jedisClient

        return result;
    }*/

    @Override
    public Object invoke(MethodInvocation invocation) {
        for (int i = 0; i < 100; i++)
            System.out.print("*");
        System.out.println();

        Method method = invocation.getMethod();
        JedisCache config = method.getAnnotation(JedisCache.class);
        if (config != null) {
            String key = config.field();
            if (StringUtils.isEmpty(key))
                key = String.format(
                        "%s.%s(%s)",
                        method.getDeclaringClass().getName(),
                        method.getName(),
                        Arrays.toString(invocation.getArguments()));
            String field = config.field();
            if (StringUtils.isEmpty(field))
                field = Arrays.toString(invocation.getArguments());
            System.out.println("key=" + key + ",field" + field);
        }

        for (int i = 0; i < 100; i++)
            System.out.print("*");
        System.out.println();
        return null;
    }
}
