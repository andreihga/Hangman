package Database;

import Entity.Words;

import java.util.List;

public class WordsDAO extends DbInitializer{

    public void insertWord(Words word){
        openSessionAndTransaction();
        session.persist(word);
        closeSessionAndTransaction();
    }

//    public List<Words> showAllWords(){
//        openSessionAndTransaction();
//        List<Words> words = ; // here you left
//
//        closeSessionAndTransaction();
//        return words;
    }

