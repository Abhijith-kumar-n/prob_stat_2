
package com.jsonMapper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(
			 "/registration**",
				"/js/**",
				"/css/**",
				"/img/**",
					"/mappedData/**",
					"/userMappings/**",
			 		"/Master/**"
			).permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/users/login")
			.loginProcessingUrl("/perform_login")
			.usernameParameter("userName")
			.passwordParameter("password")
			.defaultSuccessUrl("http://localhost:3000/Mapping")
			.permitAll()
			.and()
		.csrf()
			.disable()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout");
		http.csrf().disable();
		http.antMatcher("/users/**");
	}


}

