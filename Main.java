package hotel.worker;

public class Main {

    public static void main(String[] args) {

        WorkerManager manager = new WorkerManager();

        manager.addWorker("Omar", "0100000000", "omar@mail.com", 5000.0, "Receptionist");
        manager.addWorker("Sara", "0111111111", "sara@mail.com", 6000.0, "Cleaner");

        System.out.println("Workers after add:");
        for (Worker w : manager.listWorkers()) {
            System.out.println(w);
        }

        manager.editWorker(1, "Omar Updated", "0100000000", "omar2@mail.com", 5200.0, "Receptionist");

        System.out.println("\nWorkers after edit:");
        for (Worker w : manager.listWorkers()) {
            System.out.println(w);
        }

        manager.deleteWorker(2);

        System.out.println("\nWorkers after delete:");
        for (Worker w : manager.listWorkers()) {
            System.out.println(w);
        }
    }
}
