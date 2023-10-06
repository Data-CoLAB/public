package org.datacolab;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.enterprise.context.ApplicationScoped;

/**
 * Simulates the database layer by writing to a file instead
 */
@ApplicationScoped
public class dao {
	public void appendToTxtFile(String fileName, String content) throws IOException {
		try {
			String filePath = "/tmp/data/output/result.txt"; // File path in the working directory
			if (!Files.exists(Paths.get(filePath))) {
				Files.createFile(Paths.get(filePath)); // Create the file if it doesn't exist
			}
			FileWriter writer = new FileWriter(filePath, true);
			writer.write(fileName + ": " + content + "\n");
			writer.close();
		} catch (IOException e) {
			throw e;
		}
	}
}
