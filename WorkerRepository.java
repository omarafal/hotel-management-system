package hotel.worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerRepository {

    private final Map<Integer, Worker> workers = new HashMap<>();
    private int idCounter = 1;

    public synchronized Worker add(Worker worker) {
        worker.setId(idCounter++);
        workers.put(worker.getId(), worker);
        return worker;
    }

    public synchronized Worker update(int id, Worker worker) {
        if (!workers.containsKey(id)) {
            throw new IllegalArgumentException("Worker with id " + id + " does not exist");
        }
        worker.setId(id);
        workers.put(id, worker);
        return worker;
    }

    public synchronized void delete(int id) {
        workers.remove(id);
    }

    public synchronized Worker get(int id) {
        return workers.get(id);
    }

    public synchronized List<Worker> list() {
        return new ArrayList<>(workers.values());
    }
}
