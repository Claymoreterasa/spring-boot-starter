package xdu.bdilab.springbootstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import xdu.bdilab.springbootstarter.common.Initializer;

@EnableSwagger2
@SpringBootApplication
public class SpringBootStarterApplication {

	@Autowired
	private Initializer initializer;

	/**
	 * 初始化管理员
	 * @param
	 */
	@Bean
	public CommandLineRunner initialize(){
		return strings -> {
			initializer.initialize();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterApplication.class, args);
	}

}
