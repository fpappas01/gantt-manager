package reporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import manager.Task;

public class TextReporter extends Reporter {

	public TextReporter(ArrayList<Task> taskList) {
		super(taskList);
	}

	@Override
	public void saveFile(String path) {
		File fout = new File(path);
		try (FileOutputStream fos = new FileOutputStream(fout)) {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			String delimiter = "\t";
			bw.write("TaskId" + delimiter + "TaskText" + delimiter +
					"MamaId" + delimiter + "Start" + delimiter +
					"End" + delimiter + "Cost");
			bw.newLine();
			for (int i = 0; i < this.getTaskList().size(); i++) {
				bw.write(this.getTaskList().get(i).toString(delimiter));
				bw.newLine();
			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
