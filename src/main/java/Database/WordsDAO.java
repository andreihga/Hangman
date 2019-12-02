package Database;

import CustomerExceptions.InvalidId;
import CustomerExceptions.InvalidName;
import Entity.Words;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class WordsDAO extends DbInitializer {


    public void insertWord(Words word) {
        openSessionAndTransaction();
        session.persist(word);
        closeSessionAndTransaction();
    }

    public List<Words> viewAllWords() {
        openSessionAndTransaction();
        Query query = session.createNamedQuery("show_all_words");
        List<Words> allWords = query.getResultList();
        closeSessionAndTransaction();
        return allWords;
    }

    public void deleteWordsById(Words word) {
        if (word.getName() != null) {
            openSessionAndTransaction();
            session.delete(word);
            closeSessionAndTransaction();
        } else {
            throw new InvalidName();
        }
    }

    public void updateWordById(Words word){
        if (word.getId() != 0){
            openSessionAndTransaction();
            session.update(word);
            closeSessionAndTransaction();
        } else {
            throw new InvalidId();
        }
    }

}

