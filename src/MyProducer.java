import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyProducer implements Runnable {

	int max;
	List<Integer> list;
	ReentrantLock lock;
	Condition add;
	Condition remove;
	int st;

	public MyProducer(List<Integer> list, int i, ReentrantLock lock, Condition add, Condition remove, int s) {

		this.list = list;
		this.max = i;
		this.lock = lock;
		this.add = add;
		this.remove = remove;
		this.st =s;
	}

	@Override
	public void run() {
		
		int i = st;
		
		while(i<=st+10) {
			

			lock.lock();
			while (list.size() >= max) {
				System.out.println("List is full... waiting for empty space...");
				try {
					remove.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
		
			list.add(i);
			System.out.println("Producer is adding : " + i );
			try {
				add.signal();
				Thread.sleep(700);
			} catch (InterruptedException e) {
				

			} finally {
				lock.unlock();
			}
			
			i++;
		}
	}

}
