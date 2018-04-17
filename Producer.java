import java.util.Queue;
import java.util.Random;

/*
 * The Producer intakes a steady stream of random chars within the bounds of 
 * ASCII and adds it to a Queue. This Queue is shared with the Consumer.
 */
public class Producer extends Thread {

	private Queue<Integer> Q; // Shared Queue

	public Producer(Queue<Integer> q) {
		this.Q = q;
	}

	private void handleEvent(int c) throws InterruptedException {
		while (c < 33)
			c = (c + new Random().nextInt(127)) % 127;
		MAIN.mutex.acquire();

		this.Q.add(c);
		System.out.println("Produced: " + this.Q);

		MAIN.mutex.release();
		MAIN.items.release();

		// Simulate waiting for next event
		Thread.sleep(new Random().nextInt(MAIN.P));
	}

	public void run() { // Steady flow of data (event)
		while (true) {
			try {
				this.handleEvent(Math.abs(new Random().nextInt(127)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
