package com.messenger.chatapp.network;
//has 2 threads - read and write

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import com.messenger.chatapp.utils.ConfigReader;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
	
	
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PortNo"));
		socket = new Socket(ConfigReader.getValue("Server_IP"),PORT);
		out = socket.getOutputStream();
		in = socket.getInputStream();
		this.textArea = textArea;
		readMessages();
		
		/*
		 * 
		System.out.println("Client comes");
		System.out.println("Enter the Message to be sent to Server : ");
		Scanner sc = new Scanner(System.in);
		String message = sc.nextLine();
		OutputStream out = socket.getOutputStream();	//write bytes on network
		out.write(message.getBytes());
		System.out.println("Message sent to the Server");
		sc.close();
		out.close();
		socket.close();
		*
		*/
	}
	public void sendMessage(String message) throws IOException {
		message = message+"\n";
		out.write(message.getBytes());
	}
	public void readMessages() {
		worker = new ClientWorker(in, textArea);	//calling a read thread
		worker.start();
	}
	
	
//	public static void main(String[] args) throws UnknownHostException, IOException {
//		// TODO Auto-generated method stub
//		Client client = new Client();
//		
//
//	}

	
}
