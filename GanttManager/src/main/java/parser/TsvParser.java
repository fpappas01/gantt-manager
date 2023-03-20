package parser;

import java.util.ArrayList;

import dom2app.SimpleTableModel;
import manager.Task;
import manager.TaskManager;

public class TsvParser extends Parser {

	@Override
	public SimpleTableModel loadFile(String path, String delimiter) {
		ArrayList<String[]> pData = new ArrayList<String[]>();
		String[] pColumnNames = { "TaskId", "TaskText", "MamaId", "Start", "End", "Cost" };
		ArrayList<Task> unsortedData = getTasksFromFile(path, delimiter);
		TaskManager manager = new TaskManager();
		manager.setTaskList(unsortedData);
		manager.calculateAllMissingFields();
		manager.sortTasks();
		for (Task task : manager.getTaskList()) {
			pData.add(manager.convertTaskToStrings(task, "\t"));
		}
		return new SimpleTableModel("/src/main/java", "GanttManager", pColumnNames, pData);
	}

}
