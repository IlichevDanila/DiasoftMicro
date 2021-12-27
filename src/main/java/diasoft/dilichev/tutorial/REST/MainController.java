package diasoft.dilichev.tutorial.REST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import diasoft.dilichev.tutorial.LogRequest;
import diasoft.dilichev.tutorial.REST.NotFoundException;
import diasoft.dilichev.tutorial.REST.objects.Greeting;
import diasoft.dilichev.tutorial.REST.objects.Primes;
import diasoft.dilichev.tutorial.REST.objects.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired
    private GreetingService greetingService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private Sequence seq = new Sequence();

    @LogRequest
    @GetMapping("/greeting")
    public List<Greeting> GetGreeting(@RequestParam(value = "id", defaultValue = "-1") int id) {
        if(id < 0) {
            return greetingService.getAll();
        }
        List<Greeting> res = new ArrayList<Greeting>();
        res.add(greetingService.get(id));

        return res;
    }

    @PostMapping("/greeting")
    public Greeting AddGreeting(@RequestBody Greeting body) {
        return greetingService.add(body);
    }

    @PutMapping("/greeting")
    public Greeting UpdateGreeting(@RequestBody Greeting body) {
        return greetingService.update(body);
    }

    @DeleteMapping("/greeting")
    public void DeleteGreeting(@RequestBody int id) {
        greetingService.remove(id);
    }

    @LogRequest
    @GetMapping("/primes")
    public Primes primes(@RequestParam(value = "count", defaultValue = "10") int count) {
        return new Primes(counter.incrementAndGet(), count);
    }

    @GetMapping("/sequence")
    public Sequence getSeq(@RequestParam(value = "count", defaultValue = "-1") int count)
    {
        //Return all the sequence
        if(count < 0)
        {
            return seq;
        }
        if(count > seq.getCount())
        {
            throw new NotFoundException();
        }

        //Return only count elements
        return seq.getSubsequence(count);
    }

    @PostMapping("/sequence")
    public Sequence updateSeq(@RequestBody String SCount)
    {
        int count = Integer.parseInt(SCount);

        System.out.println("Post " + count);

        //Add one number
        if(count < 0)
        {
            count = seq.getCount();
        }

        //Add numbers until we got enough
        while(seq.getCount() <= count)
        {
            seq.add();
        }

        return seq;
    }

    @PutMapping("/sequence/{idx}")
    public Sequence changeSeq(@PathVariable int idx,
                              @RequestBody String Sval)
    {
        int val = Integer.parseInt(Sval);
        System.out.println("Put " + val);
        //Update last value
        if(idx < 0)
        {
            idx = seq.getCount() - 1;
        }

        seq.change(idx, val);
        return seq;
    }

    @DeleteMapping("/sequence/{idx}")
    public Sequence deleteSeq(@PathVariable int idx)
    {
        System.out.println("Delete " + idx);
        seq.delete(idx);

        return seq;
    }
}