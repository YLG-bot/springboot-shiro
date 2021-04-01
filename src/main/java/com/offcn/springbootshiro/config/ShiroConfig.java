package com.offcn.springbootshiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public IniRealm getIniRealm(){
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        return iniRealm;
    }
    //
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

   @Bean
   public JdbcRealm getJdbcRealm(){
       JdbcRealm jdbcRealm = new JdbcRealm();
       return jdbcRealm;
   }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(IniRealm iniRealm){
   /* public DefaultWebSecurityManager getDefaultWebSecurityManager(JdbcRealm jdbcRealm){*/
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //securityManager要完成校验，需要realm
        securityManager.setRealm(iniRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        //过滤器就是shiro进行权限校验的核心，进行认证和授权是需要SecurityManager
        filter.setSecurityManager(securityManager);

        //设置shiro的拦截规则
        //anon 匿名用户可访问  authc 认证用户可访问  user 使用RemeberMe的用户可访问  perms 对应权限可访问  role 对应的角色可访问
        Map<String,String> filterMap = new HashMap<>();
        filterMap.put("/","anon");
        filterMap.put("/login.html","anon");
        filterMap.put("/regist.html","anon");
        filterMap.put("/index.html","anon");
        filterMap.put("/user/login","anon");
        filterMap.put("/user/regist","anon");
        filterMap.put("/static/**","anon");
        filterMap.put("/test","anon");
        filterMap.put("/test.html","anon");
        filterMap.put("/**","authc");
        filter.setFilterChainDefinitionMap(filterMap);

        //设置默认的登录页面
        filter.setLoginUrl("/login.html");
        //设置未授权访问的页面路径
        filter.setUnauthorizedUrl("/login.html");
        return filter;
    }

}
