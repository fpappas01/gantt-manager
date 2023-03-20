package reporter;

import java.util.ArrayList;

import manager.Task;

public abstract class Reporter {

	private ArrayList<Task> taskList;

	public Reporter(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}

	public abstract void saveFile(String path);

	protected ArrayList<Task> getTaskList() {
		return this.taskList;
	}
}
