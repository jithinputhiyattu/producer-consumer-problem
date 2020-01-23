import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyConsumer implements Runnable {

	List<Integer> list;
	ReentrantLock lock;
	Condition add;
	Condition remove;

	public MyConsumer(List<Integer> list, ReentrantLock lock, Condition add, Condition remove) {
		this.list = list;
		this.lock = lock;
		this.add = add;
		this.remove = remove;
	}

	@Override
	public void run() {

		while (true) {
			
			lock.lock();

			while (list.size() == 0) {
				System.out.println("Consumer is Waiting for an element....");
				try {
					add.await();
				} catch (InterruptedException e) {
 
				}

			}


			System.out.println("Consumer is consuming : " + list.remove(0));
 

			try {
				remove.signal();
				Thread.sleep(500);
			} catch (InterruptedException e) {

			} finally {
				lock.unlock();
			}

		}

	}

}
