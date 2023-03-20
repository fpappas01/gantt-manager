package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ComplexTask extends Task {

	private ArrayList<Task> listWithSimpleTasks = new ArrayList<Task>();
	private String status = "Complex";

	public ComplexTask(int taskID, int mamaID, int startDate, int endDate, int cost, String taskText) {
		super(taskID, mamaID, startDate, endDate, cost, taskText);

	}

	@Override
	public boolean isTopLevel() {
		if (this.getMamaId() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isSubTask() {
		return false;
	}

	public void addSubtask(Task task) {
		this.listWithSimpleTasks.add(task);
	}

	@Override
	public boolean isComplex() {
		if (status.equals("Complex")) {
			return true;
		}
		return false;
	}

	public ArrayList<Task> getListWithSimpleTasks() {
		return this.listWithSimpleTasks;
	}

	public void setListWithSimpleTasks(ArrayList<Task> l) {
		this.listWithSimpleTasks = l;
	}

	public void sortSubTasks() {
		Collections.sort(this.listWithSimpleTasks, Comparator.comparing(Task::getStartDate)
				.thenComparing(Task::getTaskId));
	}

	public void calculateMissingFields() {
		Task task;
		int cost = 0;
		task = Collections.max(this.listWithSimpleTasks, Comparator.comparing(t -> t.getEndDate()));
		this.setEndDate(task.getEndDate());
		task = Collections.min(this.listWithSimpleTasks, Comparator.comparing(t -> t.getStartDate()));
		this.setStartDate(task.getStartDate());
		for (Task stask : this.listWithSimpleTasks) {
			cost += stask.getCost();
		}
		this.setCost(cost);
	}

}
