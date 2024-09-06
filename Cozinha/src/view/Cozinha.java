package view;

import controller.*;
import java.util.concurrent.Semaphore;

public class Cozinha
{
	public static void main(String[] args)
	{
		Semaphore semaforo = new Semaphore(1);
		for (int i = 1; i <= 5; i++) {
			Thread t = new ThreadPratos(i, semaforo);
			t.start();
		}
	}
}
