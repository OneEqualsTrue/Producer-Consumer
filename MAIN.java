import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class MAIN {

	// Global Semaphores
	static Semaphore mutex = new Semaphore(1);
	static Semaphore items = new Semaphore(0);

	// Simulation times for Producer (P) and Consumer (C)
	static int C = 100;
	static int P = 113; // Calibrated for fair Queue size

	public static void main(String[] args) {

		Queue<Integer> Q = new LinkedList<>(); // Shared Queue

		new Producer(Q).start();
		new Consumer(Q).start();
	}

}
