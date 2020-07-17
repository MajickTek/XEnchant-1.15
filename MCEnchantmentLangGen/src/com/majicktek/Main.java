package com.majicktek;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("en_us.json"));
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of loops:");
		int numLoop = input.nextInt();
		writer.write("{\n");
		for (int i = 1; i < numLoop; i++) {
			writer.write("\t\"enchantment.level." + i + "\"" + ": " + "\"" + RomanNumeral.toRoman(i) + "\",\n");
		}
		writer.write("}");
		writer.close();
	}

}
