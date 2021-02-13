package Main;

import Database.WordsDAO;
import Service.Service;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        service.action();
        System.out.println("I am main");
    }
}
