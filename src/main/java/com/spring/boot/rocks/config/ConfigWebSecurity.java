package com.spring.boot.rocks.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.spring.boot.rocks")
public class ConfigWebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	DataSource dataSource;


	@Autowired
	@Qualifier("persistentTokenRepository")
	private PersistentTokenRepository persistentTokenRepository;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/passwordforgot", "/passwordreset", "/register","/login","/confirm","/resources/**","/webjars/**","/css/**", "/js/**", "/img/**","/css/theme/**","/userEdit").permitAll();
		httpSecurity.authorizeRequests().antMatchers("/userDelete/**","/superAdminActivateUser","/superAdminActivateUser/**").access("hasAuthority('ADMIN')");
		httpSecurity.authorizeRequests().antMatchers("/userEdit/**").access("hasAuthority('ADMIN') or hasAuthority('EDITOR')")
				//.antMatchers("/viewuser/**").permitAll()
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
//				.failureUrl("/login")
//				   .defaultSuccessUrl("/", true)

				.and().logout().invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/login?logout").permitAll().and().rememberMe().rememberMeParameter("remember-me")
				.tokenRepository(persistentTokenRepository).userDetailsService(userDetailsService).and().csrf()
				.disable().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
	

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

//	@Bean
//	public SimpleUrlHandlerMapping faviconHandlerMapping() {
//		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//		mapping.setOrder(Integer.MIN_VALUE);
//		mapping.setUrlMap(Collections.singletonMap("favicon.ico", faviconRequestHandler()));
//		return mapping;
//	}

//	@Bean
//	protected ResourceHttpRequestHandler faviconRequestHandler() {
//		ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
//		requestHandler.setLocations(Arrays.<Resource>asList(new ClassPathResource("/")));
//		return requestHandler;
//	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
		web.ignoring().antMatchers("/css/**");
		web.ignoring().antMatchers("/js/**");
		web.ignoring().antMatchers("/img/**");
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}

}