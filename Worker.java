package hotel.worker;

public class Worker {

    private int id;
    private String name;
    private String phone;
    private String email;
    private double salary;
    private String jobTitle;

    public Worker(int id, String name, String phone, String email,
                  double salary, String jobTitle) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        this.jobTitle = jobTitle;
    }

    public Worker(String name, String phone, String email,
                  double salary, String jobTitle) {
        this(0, name, phone, email, salary, jobTitle);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
