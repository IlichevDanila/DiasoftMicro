package diasoft.dilichev.tutorial;

import diasoft.dilichev.tutorial.REST.GreetingService;
import diasoft.dilichev.tutorial.REST.objects.Greeting;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class GreetingServiceTest {
    @TestConfiguration
    static class GreetingServiceTestConfig
    {
        @Bean
        GreetingService greetingServiceTest()
        {
            return new GreetingService();
        }
    }

    @Autowired
    GreetingService greetingService;

    public Greeting createGreeting()
    {
        return greetingService.add(new Greeting(1, "Test content"));
    }

    @Test
    public void getAllTest()
    {
        createGreeting();
        Assert.assertEquals(greetingService.getAll().size(), 1);
    }

    @Test
    public void getTest()
    {
        Greeting test = createGreeting();
        Greeting found = greetingService.get(test.getId());
        Assert.assertEquals(test, found);
    }

    @Test
    public void addTest()
    {
        createGreeting();

        Assert.assertTrue(greetingService.getAll().size() != 0);
    }

    @Test
    public void updateTest()
    {
        Greeting test = createGreeting();
        Greeting new_gr = new Greeting(1, "New content");

        greetingService.update(new_gr);

        Assert.assertEquals(greetingService.get(1).getContent(), "New content");
    }

    @Test
    public void removeTest()
    {
        createGreeting();

        greetingService.remove(1);

        Assert.assertEquals(greetingService.get(1), null);
    }
}
