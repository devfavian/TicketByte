package utils;

import java.io.*;

public class Counter {
	public static int read(long guildID) {
		File file = new File("counter_" + guildID + ".txt");
		if(!file.exists()) return 0;
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			return Integer.parseInt(br.readLine());
		} catch(IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void write(long guildID, int value) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("counter_" + guildID + ".txt"))){
			bw.write(String.valueOf(value));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
