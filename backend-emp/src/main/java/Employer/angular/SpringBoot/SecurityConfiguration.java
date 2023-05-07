package Employer.angular.SpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import Employer.angular.SpringBoot.services.AuthDetailsService;

@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{
	@Autowired
	private AuthDetailsService userDetailsService;
	
	@Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.addAllowedOrigin("*");
	    config.addAllowedMethod("*");
	    config.addAllowedHeader("*");
	    source.registerCorsConfiguration("/**", config);
	    return source;
	}
	
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll().and()
	        .cors().and()
	        .csrf().disable();
	}
	    

	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	} 
	    


}
