package boardProject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["boardProject.domain"])
class BoardProjectApplication

fun main(args: Array<String>) {
    runApplication<BoardProjectApplication>(*args)
}