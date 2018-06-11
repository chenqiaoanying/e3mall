package cn.e3mall.common.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JedisCache {

    Action action();

    String field() default "";

    String[] key() default "";

    int expire() default -1;

    enum Action {
        WRITE, READ
    }
}
