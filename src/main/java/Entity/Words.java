package Entity;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
    @NamedQuery(name = "show_all_words",
                    query = "")
})

@Entity
@Table(name = "words", schema = "hangman_words")

public class Words {
    @Id
    private int id;

    @Column(name = "nameWord")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Words{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Words words = (Words) o;
        return id == words.id &&
                Objects.equals(name, words.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
