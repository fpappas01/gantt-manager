package reporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import manager.Task;

public class HtmlReporter extends Reporter {

	public HtmlReporter(ArrayList<Task> taskList) {
		super(taskList);
	}

	@Override
	public void saveFile(String path) {
		Task task;
		String htmlTableString = "";
		htmlTableString += "<tr> <td> TaskId </td> <td> TaskText </td> <td> MamaId </td> <td> Start </td> <td> End </td> <td> Cost </td> </tr>";
		for (int i = 0; i < this.getTaskList().size(); i++) {
			task = this.getTaskList().get(i);
			htmlTableString += String.format(
					"<tr> <td>%d</td> <td>%s</td> <td>%d</td> <td>%d</td> <td>%d</td> <td>%d</td> </tr>",
					task.getTaskId(), task.getTaskText(), task.getMamaId(),
					task.getStartDate(), task.getEndDate(), task.getCost());
		}
		String htmlDocument = "<!doctype html>"
				+ "<html>"
				+ "<head>"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"windows-1253\">"
				+ "<title>Gantt Project Data</title>"
				+ "</head>"
				+ "<body>"
				+ "<table>"
				+ htmlTableString
				+ "</table>"
				+ "</body>"
				+ "</html>";
		File fout = new File(path);
		try (FileOutputStream fos = new FileOutputStream(fout)) {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(htmlDocument);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
