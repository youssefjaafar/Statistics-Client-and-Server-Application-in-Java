import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class StatisticsServer {

	public static void main(String[] args) throws Exception {
		try (var listener = new ServerSocket(6000))
		{
			System.out.println("The statistics server is running...");
			var pool = Executors.newFixedThreadPool(20);
			
			while(true)
			{
				pool.execute(new StatThread
						(listener.accept()));
			}
		}
	}
}
