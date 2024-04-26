package com.messenger.chatapp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.messenger.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList <ServerWorker> workers = new ArrayList<>();		//con tains all client sockets //to prevent loss of previous/ old threads using arraylist
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PortNo"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Start and Waiting for Clients to join...");
		handleClientRequest();
	}
	
	//Multiple client Handshaking
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept(); 		//handshaking
			//per client per thread/worker
			ServerWorker serverWorker = new ServerWorker(clientSocket, this);		//creating a new worker //this=server
			workers.add(serverWorker);		//store old threads in arraylist
			serverWorker.start();
		}
		
	}
	
	/* SINGLE CLIENT
	public Server() throws IOException {
		
		int PORT = Integer.parseInt(ConfigReader.getValue("PortNo"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started ... waiting for client connection...");
		Socket socket = serverSocket.accept(); 		//handshaking
		System.out.println("Client Joins the server");
		InputStream in = socket.getInputStream();	//read bytes from the network
		byte arr[] = in.readAllBytes();
		String str = new String(arr);	//bytes converted to string
		System.out.println("Message Received from Client : "+ str);
		in.close();
		socket.close();
	}
	*/
	
	public static void main(String[] args) throws IOException {
		Server server = new Server();
	}
}
