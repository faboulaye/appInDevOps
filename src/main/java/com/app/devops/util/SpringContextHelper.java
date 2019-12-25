package com.app.devops.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Optional;

public class SpringContextHelper implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static final <P extends Object> Optional<P> getBean(Class<P> clazz, String name) {
        return Optional.ofNullable(context)
                .map(ctx -> ctx.getBean(name, clazz));
    }
}
