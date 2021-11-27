package diasoft.dilichev.tutorial;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @LogRequest
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "Diasoft") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @LogRequest
    @GetMapping("/primes")
    public Primes primes(@RequestParam(value = "count", defaultValue = "10") int count) {
        return new Primes(counter.incrementAndGet(), count);
    }
}