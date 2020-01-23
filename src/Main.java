import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

	public static void main(String[] args) {
		
		
		List<Integer> list = new ArrayList<>();
		ReentrantLock lock = new ReentrantLock(true);
		
		Condition add = lock.newCondition();
		Condition remove = lock.newCondition();
		
		
		MyProducer p1 = new MyProducer(list, 10, lock, add, remove,1);
		MyProducer p2 = new MyProducer(list, 10, lock, add, remove, 100);
		MyProducer p3 = new MyProducer(list, 10, lock, add, remove, 500);
		MyProducer p4 = new MyProducer(list, 10, lock, add, remove,1000);
		MyConsumer c1 = new MyConsumer(list,lock, add, remove);
		
		
		Thread t1 = new Thread(p1);
		Thread t11 = new Thread(p2);
		Thread t12 = new Thread(p3);
		Thread t13 = new Thread(p4);
		
		
		Thread t2 = new Thread(c1);
		
		t1.start();
		t11.start();
		t12.start();
		t13.start();
		t2.start();
		
		


	}

}
