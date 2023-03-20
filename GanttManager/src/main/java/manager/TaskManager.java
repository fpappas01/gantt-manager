package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dom2app.SimpleTableModel;

public class TaskManager {
	private ArrayList<Task> taskList = new ArrayList<>();
	private ArrayList<Task> topLevelTaskList = new ArrayList<>();
	private final String[] pColumnNames = { "TaskId", "TaskText", "MamaId", "Start", "End", "Cost" };

	public ArrayList<Task> getTaskList() {
		return this.taskList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}

	public void addToList(Task task) {
		taskList.add(task);
	}

	public SimpleTableModel getTopLevelTasks() {
		ArrayList<String[]> topLevelTasksList = new ArrayList<String[]>();
		for (Task task : this.taskList) {
			if (task.isTopLevel()) {
				topLevelTasksList.add(convertTaskToStrings(task, "\t"));
			}
		}
		return new SimpleTableModel("/src/main/java", "GanttManager",
				pColumnNames, topLevelTasksList);
	}

	public SimpleTableModel getTasksByPrefix(String prefix) {
		ArrayList<String[]> taskWithCertainPrefixList = new ArrayList<String[]>();
		for (Task task : this.taskList) {
			if (task.getTaskText().startsWith(prefix)) {
				taskWithCertainPrefixList.add(convertTaskToStrings(task, "\t"));
			}
		}
		return new SimpleTableModel("/src/main/java", "GanttManager",
				pColumnNames, taskWithCertainPrefixList);
	}

	public SimpleTableModel getTaskById(int id) {
		ArrayList<String[]> taskWithCertainIdList = new ArrayList<String[]>();
		for (Task task : this.taskList) {
			if (task.getTaskId() == id) {
				taskWithCertainIdList.add(convertTaskToStrings(task, "\t"));
			}
		}
		return new SimpleTableModel("/src/main/java", "GanttManager",
				pColumnNames, taskWithCertainIdList);
	}

	public String[] convertTaskToStrings(Task task, String delimiter) {

		String taskString = task.toString(delimiter);
		String[] taskStrings = taskString.split(delimiter);
		return taskStrings;
	}

	public void sortTasks() {
		ArrayList<Task> l = new ArrayList<Task>();
		ComplexTask complexTask;
		Task task;
		for (Task ttask : this.taskList) {
			if (ttask.isTopLevel()) {
				this.topLevelTaskList.add(ttask);
			}
		}
		Collections.sort(this.topLevelTaskList, Comparator.comparing(Task::getStartDate)
				.thenComparing(Task::getTaskId));
		for (int i = 0; i < topLevelTaskList.size(); i++) {
			task = topLevelTaskList.get(i);
			l.add(task);
			if (task.isComplex()) {
				complexTask = (ComplexTask) task;
				complexTask.sortSubTasks();
				this.topLevelTaskList.set(i, complexTask);
				task = topLevelTaskList.get(i);
				ArrayList<Task> s = ((ComplexTask) task).getListWithSimpleTasks();
				for (Task stask : s) {
					l.add(stask);
				}
			}
		}
		this.taskList = l;
	}

	public void calculateAllMissingFields() {
		distributeTasks();
		for (Task ctask : this.taskList) {
			if (ctask.isComplex()) {
				((ComplexTask) ctask).calculateMissingFields();
			}
		}
	}

	private void distributeTasks() {
		int difference;
		Task task;
		for (int i = 0; i < this.taskList.size() - 1; i++) {
			task = this.taskList.get(i);
			if (task.isComplex()) {
				ComplexTask complexTask = (ComplexTask) task;
				for (int j = 0; j < this.taskList.size(); j++) {
					difference = complexTask.getFirstDigitOfId() - this.taskList.get(j).getFirstDigitOfId();
					if (difference == 0 && complexTask.getTaskId() != this.taskList.get(j).getTaskId()) {
						complexTask.addSubtask(taskList.get(j));
					}
				}
				this.taskList.set(i, complexTask);
			}
		}
	}
}
