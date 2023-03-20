package backend;

import java.util.ArrayList;

import dom2app.SimpleTableModel;
import manager.TaskManager;
import parser.TsvParser;
import reporter.HtmlReporter;
import reporter.MarkdownReporter;
import reporter.TextReporter;

public class MainController implements IMainController {

    private TaskManager taskManager = new TaskManager();

    /*
     * 
     * 
     * @see backend.IMainController#load(java.lang.String, java.lang.String)
     */
    @Override
    public SimpleTableModel load(String fileName, String delimiter) {
        TsvParser tsvParser = new TsvParser();
        SimpleTableModel tableModel = tsvParser.loadFile(fileName, delimiter);
        ArrayList<String[]> tasks = (ArrayList<String[]>) tableModel.getData();
        this.taskManager.setTaskList(tsvParser.createTasksFromStrings(tasks));
        return tableModel;
    }

    /*
     * 
     * 
     * @see backend.IMainController#getTasksByPrefix(java.lang.String)
     */
    @Override
    public SimpleTableModel getTasksByPrefix(String prefix) {
        return taskManager.getTasksByPrefix(prefix);

    }

    /*
     * 
     * 
     * @see backend.IMainController#getTaskById(int)
     */
    @Override
    public SimpleTableModel getTaskById(int id) {
        return taskManager.getTaskById(id);
    }

    /*
     * 
     * 
     * @see backend.IMainController#getTopLevelTasks()
     */
    @Override
    public SimpleTableModel getTopLevelTasks() {
        return taskManager.getTopLevelTasks();
    }

    /*
     *
     * 
     * @see backend.IMainController#createReport(java.lang.String,
     * backend.ReportType)
     */
    @Override
    public int createReport(String path, ReportType type) {
        switch (type) {
            case TEXT:
                TextReporter textReporter = new TextReporter(taskManager.getTaskList());
                textReporter.saveFile(path);
                return 1;
            case MD:
                MarkdownReporter markdownReporter = new MarkdownReporter(taskManager.getTaskList());
                markdownReporter.saveFile(path);
                return 1;
            case HTML:
                HtmlReporter htmlReporter = new HtmlReporter(taskManager.getTaskList());
                htmlReporter.saveFile(path);
                return 1;
            default:
                System.out.println("Invalid report type: " + type);
                return -1;

        }
    }
}