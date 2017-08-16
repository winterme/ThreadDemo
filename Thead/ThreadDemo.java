package com.chenparty.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread extends Thread{ //是一个线程的主体类
	private String title;
	public MyThread(String title) {
		this.title = title;
	}
	private int num = 0;
	@Override
	public void run() { //所有的线程从此处开始执行
		while (num<10) {
			print();
		}
	}
	public synchronized void print() {
		System.out.println(this.title+",num = "+num+","+Thread.currentThread().getName());
		num++;
	}
}

class RunnaleDemo implements Runnable{
	private String title;
	private int num ;
	
	public RunnaleDemo(String title) {
		super();
		this.title = title;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(num<100) {
			print();
		}
	}
	
	public synchronized void print() {
		if(num<=100) {
			System.out.println(num+","+Thread.currentThread().getName());
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		num++;
	}
	
}

class CallableDemo implements Callable<String> {
	private int num = 0;
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			print();
		}
		return "票卖完了，下次吧。。。";
	}
	
	public synchronized void print() {
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(num<10) {
			System.out.println(num+", "+Thread.currentThread().getName());
		}
		num++;
	}
}

public class ThreadDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		//使用Callable实现多线程，并取得返回值
		CallableDemo callable = new CallableDemo();
		FutureTask<String> task1 = new FutureTask<>(callable);
		FutureTask<String> task2 = new FutureTask<>(callable);
		FutureTask<String> task3 = new FutureTask<>(callable);
		Thread thread1 = new Thread(task1);
		Thread thread2 = new Thread(task2);
		Thread thread3 = new Thread(task3);
		thread1.start();
		thread2.start();
		thread3.start();
		System.out.println(task1.get());
		System.out.println(task2.get());
		System.out.println(task3.get());
		
		/**
		 //使用Runnable实现多线程
		RunnaleDemo runnaleDemo = new RunnaleDemo("张子强");
		Thread t1 = new Thread(runnaleDemo);
		Thread t2 = new Thread(runnaleDemo);
		Thread t3 = new Thread(runnaleDemo);
		t1.start();
		t2.start();
		t3.start();
		*/
		
		/**
		 //使用Thread类实现多线程
		MyThread target = new MyThread("吴佳利");
		Thread thread1 = new Thread(target);
		Thread thread2 = new Thread(target);
		Thread thread3 = new Thread(target);
		
		thread1.start();
		thread2.start();
		thread3.start();
		*/
		
	}
}
