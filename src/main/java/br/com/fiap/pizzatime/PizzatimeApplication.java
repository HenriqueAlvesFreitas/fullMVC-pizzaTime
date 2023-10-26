package br.com.fiap.pizzatime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@SpringBootApplication
public class PizzatimeApplication implements WebMvcConfigurer {

	private final LocaleChangeInterceptor localInterceptor;

	public PizzatimeApplication(LocaleChangeInterceptor localInterceptor){
		this.localInterceptor = localInterceptor;
	}

	public static void main(String[] args) {
		SpringApplication.run(PizzatimeApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(localInterceptor);
	}

}
