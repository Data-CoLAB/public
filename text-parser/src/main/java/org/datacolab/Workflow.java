package org.datacolab;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;

/**
 * Business Layer
 */
@ApplicationScoped
public class Workflow {

	private static final String RESOURCE_FOLDER = "files/";

	@Inject
	dao db;

	@Inject
	Utils utils;

	private List<String> doneList = new ArrayList<>();
	private List<String> pendingList = new ArrayList<>();

	public void processFile(String fileName) {
		try {
			// Don't process the same file twice
			if (doneList.contains(fileName)) {
				System.out.println("File: "+fileName+", duplicated so it was discarded");
				return;
			}
			// Add to the pending queue
			pendingList.add(fileName);

			// Loads file to process and get the text content
			String content = loadFile(fileName);

			// Simulate processing time, random time in seconds between 0 and 5
			int delay = (new Random()).nextInt(5);
			Thread.sleep(delay * 1000);

			// Counts the characters this text has and increments the counter
			utils.incrementCounter(content.length());

			// Write output to a txt file
			db.appendToTxtFile(fileName, content);

			// Finally adds to the done queue so it doesn't process again
			doneList.add(fileName);
			pendingList.remove(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String loadFile(@PathParam("fileName") String fileName) {
        try {
            // Use ClassLoader to load the resource from src/main/resources
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(RESOURCE_FOLDER + fileName);

            if (inputStream != null) {
                // Read the file content
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }

                // Close resources
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();

                return content.toString();
            } else {
                return "File not found: " + fileName;
            }
        } catch (Exception e) {
            return "Error loading file: " + e.getMessage();
        }
    }

}
