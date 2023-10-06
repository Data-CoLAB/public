package org.datacolab;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Utils {
	private int totalCharactersParsed = 0;

	public synchronized void incrementCounter(int characters) {
		totalCharactersParsed+=characters;
	}

	public synchronized int getCounter() {
		return totalCharactersParsed;
	}
}
