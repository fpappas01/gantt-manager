package manager;

public class SimpleTask extends Task {

	private String status = "Simple";

	public SimpleTask(int taskID, int mamaID, int startDate, int endDate, int cost, String taskText) {
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
		if (this.isTopLevel()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isComplex() {
		if (status.equals("Simple")) {
			return false;
		}
		return true;
	}

}
