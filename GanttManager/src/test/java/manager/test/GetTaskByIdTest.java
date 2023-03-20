package manager.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import dom2app.SimpleTableModel;
import manager.TaskManager;
import parser.TsvParser;

public class GetTaskByIdTest {
    private TsvParser parser = new TsvParser();
    private TaskManager manager = new TaskManager();
    private String[] pColumnNames = { "TaskId", "TaskText", "MamaId", "Start", "End", "Cost" };
    private int[] ids = { 100, 102, 200, 303, 513 };

    @Test
    public void shouldGetOnlyTheTasksWithId1() {
        boolean isEqual = true;
        manager.setTaskList(parser.createTasksFromStrings(calcAllStrings()));
        SimpleTableModel tableModelCalculate = manager.getTaskById(ids[0]);
        SimpleTableModel tableModelExpect = new SimpleTableModel("name", "prjName", pColumnNames, getId1());
        ArrayList<String[]> l1 = (ArrayList<String[]>) tableModelExpect.getData();
        ArrayList<String[]> l2 = (ArrayList<String[]>) tableModelCalculate.getData();
        for (int i = 0; i < l1.size(); i++) {
            if (!Arrays.equals(l1.get(i), l2.get(i))) {
                isEqual = false;
            }
        }
        assertEquals(true, isEqual);
    }

    @Test
    public void shouldGetOnlyTheTasksWithId2() {
        boolean isEqual = true;
        manager.setTaskList(parser.createTasksFromStrings(calcAllStrings()));
        SimpleTableModel tableModelCalculate = manager.getTaskById(ids[1]);
        SimpleTableModel tableModelExpect = new SimpleTableModel("name", "prjName", pColumnNames, getId2());
        ArrayList<String[]> l1 = (ArrayList<String[]>) tableModelExpect.getData();
        ArrayList<String[]> l2 = (ArrayList<String[]>) tableModelCalculate.getData();
        for (int i = 0; i < l1.size(); i++) {
            if (!Arrays.equals(l1.get(i), l2.get(i))) {
                isEqual = false;
            }
        }
        assertEquals(true, isEqual);
    }

    @Test
    public void shouldGetOnlyTheTasksWithId3() {
        boolean isEqual = true;
        manager.setTaskList(parser.createTasksFromStrings(calcAllStrings()));
        SimpleTableModel tableModelCalculate = manager.getTaskById(ids[2]);
        SimpleTableModel tableModelExpect = new SimpleTableModel("name", "prjName", pColumnNames, getId3());
        ArrayList<String[]> l1 = (ArrayList<String[]>) tableModelExpect.getData();
        ArrayList<String[]> l2 = (ArrayList<String[]>) tableModelCalculate.getData();
        for (int i = 0; i < l1.size(); i++) {
            if (!Arrays.equals(l1.get(i), l2.get(i))) {
                isEqual = false;
            }
        }
        assertEquals(true, isEqual);
    }

    @Test
    public void shouldGetOnlyTheTasksWithId4() {
        boolean isEqual = true;
        manager.setTaskList(parser.createTasksFromStrings(calcAllStrings()));
        SimpleTableModel tableModelCalculate = manager.getTaskById(ids[3]);
        SimpleTableModel tableModelExpect = new SimpleTableModel("name", "prjName", pColumnNames, getId4());
        ArrayList<String[]> l1 = (ArrayList<String[]>) tableModelExpect.getData();
        ArrayList<String[]> l2 = (ArrayList<String[]>) tableModelCalculate.getData();
        for (int i = 0; i < l1.size(); i++) {
            if (!Arrays.equals(l1.get(i), l2.get(i))) {
                isEqual = false;
            }
        }
        assertEquals(true, isEqual);
    }

    @Test
    public void shouldGetOnlyTheTasksWithId5() {
        boolean isEqual = true;
        manager.setTaskList(parser.createTasksFromStrings(calcAllStrings()));
        SimpleTableModel tableModelCalculate = manager.getTaskById(ids[4]);
        ArrayList<String[]> l2 = (ArrayList<String[]>) tableModelCalculate.getData();
        if (l2.size() != 0) {
            isEqual = false;
        }
        assertEquals(true, isEqual);
    }

    private ArrayList<String[]> calcAllStrings() {
        String arr[][] = {
                { "100", "Prepare Fry", "0" },
                { "101", "Turn on burner (low)", "100", "1", "1", "10" },
                { "102", "Break eggs and pour into fry", "100", "2", "4", "10" },
                { "103", "Steer mixture to avoid sticking", "100", "5", "10", "10" },
                { "105", "Salt, pepper", "100", "5", "5", "10" },
                { "104", "Throw yellow cheese into fry", "100", "6", "12", "10" },
                { "106", "Turn burner off", "100", "12", "12", "10" },
                { "200", "Prepare the bread", "0" },
                { "201", "Heat bread in toaster", "200", "10", "12", "10" },
                { "202", "Little bit of salt, galric spice to bread", "200", "12", "12", "10" },
                { "300", "Serve eggs", "0" },
                { "301", "Put bread in plate", "300", "13", "13", "10" },
                { "302", "Put eggs on bread", "300", "14", "14", "10" },
                { "303", "Wash fry", "300", "15", "20", "10" }
        };
        ArrayList<String[]> l = new ArrayList<String[]>();
        for (int i = 0; i < arr.length; i++) {
            l.add(arr[i]);
        }
        return l;
    }

    private ArrayList<String[]> getId1() {
        ArrayList<String[]> l = new ArrayList<String[]>();
        String[] s = { "100", "Prepare Fry", "0", "10", "10", "10" };
        l.add(s);
        return l;
    }

    private ArrayList<String[]> getId2() {
        ArrayList<String[]> l = new ArrayList<String[]>();
        String[] s = { "102", "Break eggs and pour into fry", "100", "2", "4", "10" };
        l.add(s);
        return l;
    }

    private ArrayList<String[]> getId3() {
        ArrayList<String[]> l = new ArrayList<String[]>();
        String[] s = { "200", "Prepare the bread", "0", "10", "10", "10" };
        l.add(s);
        return l;
    }

    private ArrayList<String[]> getId4() {
        ArrayList<String[]> l = new ArrayList<String[]>();
        String[] s = { "303", "Wash fry", "300", "15", "20", "10" };
        l.add(s);
        return l;
    }

}

