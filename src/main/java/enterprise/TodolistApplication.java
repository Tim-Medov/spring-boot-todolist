
package enterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodolistApplication {

    public static void main(String[] args) {

        // page url: http://localhost:8080/todolist
        SpringApplication.run(TodolistApplication.class, args);
    }
}
