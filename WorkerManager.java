package hotel.worker;

import java.util.List;

public class WorkerManager {

    private final WorkerRepository repo = new WorkerRepository();

    public Worker addWorker(String name, String phone, String email,
                            double salary, String jobTitle) {

        Worker worker = WorkerFactory.createWorker(name, phone, email, salary, jobTitle);
        return repo.add(worker);
    }

    public Worker editWorker(int id, String name, String phone, String email,
                             double salary, String jobTitle) {

        Worker worker = WorkerFactory.createWorker(name, phone, email, salary, jobTitle);
        return repo.update(id, worker);
    }

    public void deleteWorker(int id) {
        repo.delete(id);
    }

    public Worker getWorker(int id) {
        return repo.get(id);
    }

    public List<Worker> listWorkers() {
        return repo.list();
    }
}
