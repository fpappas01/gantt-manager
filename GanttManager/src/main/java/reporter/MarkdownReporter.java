package reporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import manager.Task;

public class MarkdownReporter extends Reporter {

	public MarkdownReporter(ArrayList<Task> taskList) {
		super(taskList);
	}

	@Override
	public void saveFile(String path) {
		Task task;
		String topLevelTaskString, normalTaskString;
		File fout = new File(path);
		try (FileOutputStream fos = new FileOutputStream(fout)) {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			String delimiter = "  ";
			bw.write("*TaskId*" + delimiter + "*TaskText*" + delimiter +
					"*MamaId*" + delimiter + "*Start*" + delimiter +
					"*End*" + delimiter + "*Cost*");
			bw.newLine();
			for (int i = 0; i < this.getTaskList().size(); i++) {
				task = this.getTaskList().get(i);
				if (task.isTopLevel()) {
					topLevelTaskString = String.format("**%d**  **%s**  **%d**  **%d**  **%d**  **%d**",
							task.getTaskId(), task.getTaskText(), task.getMamaId(), task.getStartDate(),
							task.getEndDate(), task.getCost());
					bw.write(topLevelTaskString);
				} else {
					normalTaskString = String.format("%d	%s	%d	%d	%d	%d",
							task.getTaskId(), task.getTaskText(), task.getMamaId(), task.getStartDate(),
							task.getEndDate(), task.getCost());
					bw.write(normalTaskString);
				}
				bw.newLine();
			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
