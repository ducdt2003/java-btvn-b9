package lap_3.view;

import lap_3.entities.Worker;
import lap_3.service.WorkerService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public void menuWorker(){
        System.out.println("Chức Năng Chương Trình");
        System.out.println("1.Add worker");
        System.out.println("2.Up salary");
        System.out.println("3.Down salary");
        System.out.println("4.Display Information salary");
        System.out.println("5.Exit");
        System.out.println("Hãy chọn chức năng bạn muốn");
    }
    public void displayMenu(Scanner sc, ArrayList<Worker> workers, Worker worker){
        do {
            menuWorker();
            int choice = Integer.parseInt(sc.nextLine());
            WorkerService workerService = new WorkerService();
            switch (choice){
                case 1:
                    workerService.addWorker(sc, workers);
                    workerService.dislayWorker(workers);
                    break;
                case 2:
                    workerService.upSalari(sc, workers);
                    break;
                case 3:
                    workerService.downSalari(sc, workers);
                    break;
                case 4:
                    System.out.println("Số tiền lương Tăng đã được cập nhật lại");
                    workerService.dislayWorker(workers);
                    break;
                case 5:
                    System.out.println("Thoát chương trình");
                    workerService.dislayWorker(workers);
                    System.exit(0);
                default:
                    System.out.println("Lỗi Nhập Vào");
                    System.exit(0);
            }
        }while (true);

    }
}
