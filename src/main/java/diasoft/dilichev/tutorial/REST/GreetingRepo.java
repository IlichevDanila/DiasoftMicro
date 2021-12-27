package diasoft.dilichev.tutorial.REST;

import diasoft.dilichev.tutorial.REST.objects.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GreetingRepo extends JpaRepository<Greeting, Long> {

    List<Greeting> findAll();

    Optional<Greeting> findById(Long id);
}
