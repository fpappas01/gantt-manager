package manager;

public abstract class Task {
	private int taskID;
	private int mamaID;
	private int startDate;
	private int endDate;
	private int cost;
	private String taskText;

	public Task(int taskID, int mamaID, int startDate, int endDate, int cost, String taskText) {
		this.taskID = taskID;
		this.mamaID = mamaID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
		this.taskText = taskText;
	}

	public abstract boolean isTopLevel();

	public abstract boolean isSubTask();

	public abstract boolean isComplex();

	public int getTaskId() {
		return this.taskID;
	}

	public int getMamaId() {
		return this.mamaID;
	}

	public int getStartDate() {
		return this.startDate;
	}

	public int getEndDate() {
		return this.endDate;
	}

	public int getCost() {
		return this.cost;
	}

	public String getTaskText() {
		return this.taskText;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getFirstDigitOfId() {
		int n = this.taskID;
		while (n > 9)
			n /= 10;
		return Math.abs(n);
	}

	public String toString(String delimiter) {

		return taskID + delimiter +
				taskText + delimiter +
				mamaID + delimiter +
				startDate + delimiter +
				endDate + delimiter +
				cost;
	}
}
