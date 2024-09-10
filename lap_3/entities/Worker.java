package lap_3.entities;

public class Worker {
    private static int autoID = 0;
    private int id;
    private String name;
    private int ags;
    private double salary;
    private String workPlace;

    public Worker() {
    }

    public Worker( String name, int ags, double salary, String workPlace) {
        this.id = autoID++;
        this.name = name;
        this.ags = ags;
        this.salary = salary;
        this.workPlace = workPlace;
    }

    public static int getAutoID() {
        return autoID;
    }

    public static void setAutoID(int autoID) {
        Worker.autoID = autoID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgs() {
        return ags;
    }

    public void setAgs(int ags) {
        this.ags = ags;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ags=" + ags +
                ", salary='" + salary + '\'' +
                ", workPlace='" + workPlace + '\'' +
                '}';
    }

  /*  public void display() {
        System.out.printf("%5s %10s %10s %10s %20s\n", "ID", "Tên", "Tuổi", "Lương", "Nơi làm việc");
        System.out.printf("%5d %10s %8d %15.2f %11s\n", id, name, ags, salary, workPlace);
    }*/

}
