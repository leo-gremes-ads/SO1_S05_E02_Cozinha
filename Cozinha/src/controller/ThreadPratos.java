package controller;

import java.lang.InterruptedException;
import java.util.concurrent.Semaphore;

public class ThreadPratos extends Thread
{
	private int id;
	private Semaphore semaforo;
	private String nome;
	private int min;
	private int max;

	public ThreadPratos(int id, Semaphore semaforo)
	{
		this.id = id;
		this.semaforo = semaforo;
		if (id % 2 == 0) {
			this.nome = "Lasanha a Bolonhesa";
			this.min = 600;
			this.max = 1200;
		} else {
			this.nome = "Sopa de Cebola";
			this.min = 500;
			this.max = 800;
		}
	}
	
	@Override
	public void run()
	{
		cozinhar();
		try {
			semaforo.acquire();
			entregar();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
		}
	}

	public void cozinhar()
	{
		int tempo = (int)((Math.random() * (this.max - this.min)) + this.min);
		System.out.println(String.format("%20s", this.nome) + " (id: " + this.id + ") iniciou o cozimento.");
		double percentual = 0;
		int tempoDecorrido = 0;
		while (true) {
			try {
				sleep(Math.min(100, tempo - tempoDecorrido));
				tempoDecorrido += 100;
				if (tempoDecorrido >= tempo)
					break;
				percentual = ((double)tempoDecorrido / tempo) * 100;
				System.out.println(String.format("%20s", this.nome) + " (id: " + this.id + ") está " + String.format("%4.2f", percentual) + "% cozido.");
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		System.out.println(String.format("%20s", this.nome) + " (id: " + this.id + ") está finalizado.");
	}
	
	public void entregar()
	{
		System.out.println(">> Entregando " + this.nome + " (id: " + this.id + ").");
		try {
			sleep(500);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("> Finalizada entrega da " + this.nome + " (id: " + this.id + ").");
	}
}
