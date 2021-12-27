package diasoft.dilichev.tutorial.REST;

import diasoft.dilichev.tutorial.REST.objects.Greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GreetingService {
    @Autowired
    private GreetingRepo greetingRepo;

    @Transactional(readOnly = true, isolation= Isolation.READ_COMMITTED)
    public List<Greeting> getAll() {

        return greetingRepo.findAll();
    };

    @Transactional(readOnly = true, isolation= Isolation.READ_COMMITTED)
    public Greeting get(long id) {

        Optional<Greeting> greeting = greetingRepo.findById(id);

        if (greeting.isPresent()) {
            return greeting.get();
        }

        return null;
    };

    @Transactional(readOnly = false, isolation= Isolation.REPEATABLE_READ)
    public Greeting add(Greeting greeting) {

        Optional<Greeting> exists = greetingRepo.findById(greeting.getId());

        if (!exists.isPresent()) {
            return greetingRepo.save(greeting);
        }
        return null;
    };

    @Transactional(readOnly = false, isolation= Isolation.SERIALIZABLE)
    public Greeting update(Greeting greeting) {

        Optional<Greeting> exists = greetingRepo.findById(greeting.getId());

        if (exists.isPresent()) {
            return greetingRepo.save(greeting);
        }

        return null;
    };

    @Transactional(readOnly = false, isolation= Isolation.SERIALIZABLE)
    public void remove(long id) {
        greetingRepo.deleteById(id);
    };
}