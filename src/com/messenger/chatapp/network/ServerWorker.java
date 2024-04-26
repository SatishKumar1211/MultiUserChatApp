package com.messenger.chatapp.network;
// to manage multiple client - per client per thread/serverWorker

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

//thread = worker
//worker need a job to perform
//once job is created via runnable so write the job logic inside a run function
//assign the job to thread/worker

//public class ServerWorker implements Runnable {
public class ServerWorker extends Thread{
	private	Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	
	
	public ServerWorker(Socket clientSocket,Server server) throws IOException{
		this.server = server; 		//
		this.clientSocket = clientSocket;
		in = clientSocket.getInputStream(); 	//client data read
		out = clientSocket.getOutputStream(); 	//client data write
		System.out.println("New Client Comes");
	}
	
	@Override
	public void run() {
		//read data from the client and broadcast data to all
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while(true) {
			
				line = br.readLine();
				System.out.println("Line Read...."+line);
				if(line.equalsIgnoreCase("quit")) {
					break;		//client chat end
				}
				//out.write(line.getBytes()); 	//data send to client
				//broad cast to all
				for(ServerWorker serverWorker : server.workers) {
					line = line + "\n";
					serverWorker.out.write(line.getBytes()); 	//data broadcasted
				}
			} 
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {	//Resource cleanup when any client quits
			try {
				if(br != null) {
					br.close();
				}
				if (in != null) {
					in.close();
				}
				if(out != null) {
					out.close();
				}
				if(clientSocket != null) {
					clientSocket.close();
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
	
	
	
	/*
	 * 
	@Override
	public void run() {
		// job to perform
		//logic
		for (int i=1; i<=5; i++) {
			System.out.println("RUN I is "+i+" "+Thread.currentThread());
		}
	}
	public static void main(String[] args) {
//		ServerWorker job = new ServerWorker();
		ServerWorker worker = new ServerWorker();
		worker.start();
		
		//assign job to thread/worker
//		Thread worker = new Thread(job);
//		worker.start();		//internally it calls run()
		
		for (int j=1; j<=5; j++) {
			System.out.println("Main "+j +" "+Thread.currentThread());
		}
	}
	
	*/
