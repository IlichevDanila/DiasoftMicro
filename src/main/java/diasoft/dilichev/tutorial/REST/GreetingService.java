package diasoft.dilichev.tutorial.REST;

import diasoft.dilichev.tutorial.REST.objects.Greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {
    @Autowired
    private GreetingRepo greetingRepo;

    public List<Greeting> getAll() {

        return greetingRepo.findAll();
    };

    public Greeting get(long id) {

        Optional<Greeting> greeting = greetingRepo.findById(id);

        if (greeting.isPresent()) {
            return greeting.get();
        }

        return null;
    };

    public Greeting add(Greeting greeting) {

        Optional<Greeting> exists = greetingRepo.findById(greeting.getId());

        if (!exists.isPresent()) {
            return greetingRepo.save(greeting);
        }
        return null;
    };

    public Greeting update(Greeting greeting) {

        Optional<Greeting> exists = greetingRepo.findById(greeting.getId());

        if (exists.isPresent()) {
            return greetingRepo.save(greeting);
        }

        return null;
    };

    public void remove(long id) {
        greetingRepo.deleteById(id);
    };
}
