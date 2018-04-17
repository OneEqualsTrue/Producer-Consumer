import java.util.Queue;
import java.util.Random;

/*
 * Consumer reads an integer from a shared Queue and 'consumes' it by polling
 * the number from the Queue and printing the associated ASCII character
 */
public class Consumer extends Thread {

	private Queue<Integer> Q; // Shared Queue

	public Consumer(Queue<Integer> q) {
		this.Q = q;
	}

	public void run() {
		while (true) {
			try {
				MAIN.items.acquire();
				MAIN.mutex.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			// Moment of consumption
			System.out.println("Consumed: " + (char) (int) Q.poll());

			MAIN.mutex.release();
			
			// Simulate processing consumption
			try {
				Thread.sleep(new Random().nextInt(MAIN.C));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
