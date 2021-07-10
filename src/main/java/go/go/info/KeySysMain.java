package go.go.info;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
/**
 * references
 *  http://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html
 */

@SpringBootApplication(exclude = WebMvcAutoConfiguration.class)
public class KeySysMain implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(KeySysMain.class, args);//.getBean(BasicController.class);
	}

    @Override
    public void run(String... args) throws Exception {
    }
    
}
