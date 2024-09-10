package lap_3.service;

import lap_3.entities.Worker;

import java.util.ArrayList;
import java.util.Scanner;

public class WorkerService {
    public Worker addWorker(Scanner sc, ArrayList<Worker> workers){
            System.out.println("Nhập Name: ");
            String name = sc.nextLine();
            System.out.println("Nhập Age: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.println("Nhập salary: ");
            double salary = Double.parseDouble(sc.nextLine());
            System.out.println("Nhập workPlace: ");
            String workPlace = sc.nextLine();
            Worker worker = new Worker(name, age, salary, workPlace);
            workers.add(worker);
            return worker;
    }

    public void dislayWorker(ArrayList<Worker> workers){
        for (Worker worker: workers) {
            System.out.println(worker);
        }
    }
   public void upSalari(Scanner sc, ArrayList<Worker> workers) {
       System.out.println("Danh sách công nhân:");
       dislayWorker(workers);

       System.out.println("Nhập id công nhân muốn tăng lương:");
       int id = Integer.parseInt(sc.nextLine());

       Worker selectedWorker = null;
       for (Worker worker : workers) {
           if (worker.getId() == id) {
               selectedWorker = worker;
               break;
           }
       }
       if (selectedWorker != null) {
           System.out.println("Nhập số tiền lương thưởng thêm: ");
           double salariNumber = Double.parseDouble(sc.nextLine());
           if (salariNumber > 0) {
               selectedWorker.setSalary(selectedWorker.getSalary() + salariNumber);
               System.out.println("Lương của công nhân đã được cập nhật.");
           } else {
               System.out.println("Số tiền lương phải lớn hơn 0.");
           }
       } else {
           System.out.println("Không tìm thấy công nhân với ID này.");
       }
   }

    public void downSalari(Scanner sc, ArrayList<Worker> workers) {
        System.out.println("Danh sách công nhân:");
        dislayWorker(workers);
        System.out.println("Nhập id công nhân muốn giảm lương:");
        int id = Integer.parseInt(sc.nextLine());
        Worker selectedWorker = null;
        for (Worker worker : workers) {
            if (worker.getId() == id) {
                selectedWorker = worker;
                break;
            }
        }
        if (selectedWorker != null) {
            System.out.println("Nhập số tiền lương giảm đi: ");
            double salariNumber = Double.parseDouble(sc.nextLine());

            if (salariNumber > 0 && salariNumber <= selectedWorker.getSalary()) {
                selectedWorker.setSalary(selectedWorker.getSalary() - salariNumber);
                System.out.println("Lương của công nhân đã được cập nhật.");
            } else {
                System.out.println("Số tiền lương phải lớn hơn 0 và không được vượt quá lương hiện tại.");
            }
        } else {
            System.out.println("Không tìm thấy công nhân với ID này.");
        }
    }

}
