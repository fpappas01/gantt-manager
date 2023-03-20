package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import dom2app.SimpleTableModel;
import manager.ComplexTask;
import manager.SimpleTask;
import manager.Task;

public abstract class Parser {

	public abstract SimpleTableModel loadFile(String path, String delimiter);

	public ArrayList<Task> getTasksFromFile(String path, String delimiter) {
		ArrayList<String[]> pData = new ArrayList<String[]>();
		String[] dataRead;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while (line != null) {
				dataRead = line.split(delimiter);
				pData.add(dataRead);
				line = reader.readLine();
			}
			reader.close();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return createTasksFromStrings(pData);
	}

	public ArrayList<Task> createTasksFromStrings(ArrayList<String[]> pData) {
		ArrayList<Task> tasks = new ArrayList<Task>();
		for (int i = 0; i < pData.size(); i++) {
			String[] data = pData.get(i);
			if (data.length != 6) {
				tasks.add(new ComplexTask(Integer.parseInt(data[0]), Integer.parseInt(data[2]), 10,
						10, 10, data[1]));
			} else {
				tasks.add(new SimpleTask(Integer.parseInt(data[0]), Integer.parseInt(data[2]),
						Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]), data[1]));
			}
		}
		return tasks;
	}

}