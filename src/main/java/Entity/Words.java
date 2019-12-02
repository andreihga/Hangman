package Entity;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
    @NamedQuery(name = "show_all_words",
                    query = "SELECT w FROM Words w")
})

@Entity
@Table(name = "words", schema = "hangman_words")

public class Words {
    private static final String PRODUCTS_SEQUENCE = "products_id_sequence";
    private static final String PRODUCTS_GENERATOR = "products_generator";

    @Id
    @SequenceGenerator(name="PRODUCTS_GENERATOR", sequenceName = PRODUCTS_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = PRODUCTS_GENERATOR)
    private int id;

    @Column(name = "nameWord")
    private String name;

    public String getName() {
        return name;
    }
    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "Words = (" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ')';
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
