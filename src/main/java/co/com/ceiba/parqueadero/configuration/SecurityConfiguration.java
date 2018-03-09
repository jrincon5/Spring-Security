package co.com.ceiba.parqueadero.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import co.com.ceiba.parqueadero.service.impl.UserService;



@Configuration
@EnableWebSecurity //Habilitar la seguridad WEB
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{ // Clase que ayuda a guiar la configuracion
	
	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{ //Para añadir el user detal service
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception { //Metodo que vamos a sobrescribir para la configuracion
		http.authorizeRequests()
			.antMatchers("/css/*", "/imgs/*").permitAll() //Permitir el acceso a todos sin autenticación
			.anyRequest().authenticated() // Todas las request necesitan autenticación
			.and()
		.formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
		.usernameParameter("username").passwordParameter("password")
		.defaultSuccessUrl("/loginsuccess").permitAll()  // URL a dirigirse una vez el usuario se ha autenticado
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
		.permitAll();
		
	} 
	
}
