package hotel.worker;

public class WorkerFactory {

    public static Worker createWorker(String name, String phone, String email,
                                      double salary, String jobTitle) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Worker name is required");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }

        return new Worker(name, phone, email, salary, jobTitle);
    }
}
