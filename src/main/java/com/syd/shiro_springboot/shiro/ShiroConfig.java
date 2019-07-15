package com.syd.shiro_springboot.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     * 注意添加bean注解
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * shiro内置过滤器，可以实现权限相关的拦截器
         * 常用的过滤器：
         *     anon：无需认证（登录）可以访问。
         *     authc:必须认证才可以访问
         *     user：如果使用rememberMe功能时可以访问。
         *     perms:该资源必须得到资源权限才可以访问。
         *     role：该资源必须得到角色权限才可以访问。
         */
        //添加shiro内置过滤器
        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/add", "authc");
//        filterMap.put("/update", "authc");
        //放行部分页面
        filterMap.put("/testThymeleaf", "anon");
        filterMap.put("/login", "anon");
        //授权过滤器 (注意：当授权拦截后，shiro会自动跳转到未授权页面)
        filterMap.put("/add", "perms[user:add]");
        filterMap.put("/update", "perms[user:update]");

        //拦截所有
        filterMap.put("/*", "authc");


        //修改跳转的login页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("UserRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     *  @Bean 是说明本方法返回的对象交由Spring来管理
     */
    @Bean(name = "UserRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }


    /**
     * 配置ShiroDialect,用于thyleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
