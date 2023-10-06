package org.datacolab;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;

/**
 * Simulates the database layer
 */
@ApplicationScoped
public class dao {
	public void appendToTxtFile(String fileName, String content) throws IOException {
		System.out.println(fileName + "-" + content);
	}
}
