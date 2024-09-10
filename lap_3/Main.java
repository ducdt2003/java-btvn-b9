package lap_3;

import lap_3.entities.Worker;
import lap_3.service.WorkerService;
import lap_3.view.Menu;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Worker> workers = new ArrayList<>();
        Worker worker = new Worker();
        Menu menu = new Menu();
        menu.displayMenu(sc, workers, worker);
    }
}
