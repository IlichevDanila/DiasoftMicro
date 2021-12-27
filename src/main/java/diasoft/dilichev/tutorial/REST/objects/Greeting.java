package diasoft.dilichev.tutorial.REST.objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "greetings")
public class Greeting implements Serializable {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "content")
    private String content;

    public Greeting()
    {
        id = -1;
        content = "";
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
