package ringle.first.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Ringle1stAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ringle1stAssignmentApplication.class, args);
    }

}
