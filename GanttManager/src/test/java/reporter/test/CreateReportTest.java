package reporter.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import manager.Task;
import parser.TsvParser;
import reporter.HtmlReporter;
import reporter.MarkdownReporter;
import reporter.TextReporter;

public class CreateReportTest {

    @Test
    public void shouldBeEqualWithStringText() {
        TextReporter textReporter = new TextReporter(getList());
        String path = "src/test/resources/output/";
        textReporter.saveFile(path + "eggs.txt");
        String stringExpected = getStringExpectedText();
        String stringCalculated = readFileToString(path + "eggs.txt");
        assertEquals(stringExpected, stringCalculated);
    }

    @Test
    public void shouldBeEqualWithStringMd() {
        MarkdownReporter markdownReporter = new MarkdownReporter(getList());
        String path = "src/test/resources/output/";
        markdownReporter.saveFile(path + "eggs.md");
        String stringExpected = getStringExpectedMd();
        String stringCalculated = readFileToString(path + "eggs.md");
        assertEquals(stringExpected, stringCalculated);
    }

    @Test
    public void shouldBeEqualWithStringHtml() {
        HtmlReporter htmlReporter = new HtmlReporter(getList());
        String path = "src/test/resources/output/";
        htmlReporter.saveFile(path + "eggs.html");
        String stringExpected = getStringExpectedHtml();
        String stringCalculated = readFileToString(path + "eggs.html");
        assertEquals(stringExpected, stringCalculated);
    }

    private String getStringExpectedText() {
        String str = "TaskIdTaskTextMamaIdStartEndCost" +
                "100PrepareFry011260" +
                "101Turnonburner(low)1001110" +
                "102Breakeggsandpourintofry1002410" +
                "103Steermixturetoavoidsticking10051010" +
                "105Salt,pepper1005510" +
                "104Throwyellowcheeseintofry10061210" +
                "106Turnburneroff100121210" +
                "200Preparethebread0101220" +
                "201Heatbreadintoaster200101210" +
                "202Littlebitofsalt,galricspicetobread200121210" +
                "300Serveeggs0132030" +
                "301Putbreadinplate300131310" +
                "302Puteggsonbread300141410" +
                "303Washfry300152010";
        return str;
    }

    private String getStringExpectedMd() {
        String str = "*TaskId**TaskText**MamaId**Start**End**Cost*" +
                "**100****PrepareFry****0****1****12****60**" +
                "101Turnonburner(low)1001110" +
                "102Breakeggsandpourintofry1002410" +
                "103Steermixturetoavoidsticking10051010" +
                "105Salt,pepper1005510" +
                "104Throwyellowcheeseintofry10061210" +
                "106Turnburneroff100121210" +
                "**200****Preparethebread****0****10****12****20**" +
                "201Heatbreadintoaster200101210" +
                "202Littlebitofsalt,galricspicetobread200121210" +
                "**300****Serveeggs****0****13****20****30**" +
                "301Putbreadinplate300131310" +
                "302Puteggsonbread300141410" +
                "303Washfry300152010";
        return str;
    }

    private String getStringExpectedHtml() {
        String str = "<!doctypehtml><html><head><metahttp-equiv=\"Content-Type\"con" +
                "tent=\"text/html\"charset=\"windows-1253\"><tit" +
                "le>GanttProjectData</title></head><body><tab" +
                "le><tr><td>TaskId</td><td>TaskText</td><td>MamaId</td><td>Start</td><td>"+
                "End</td><td>Cost</td></tr><tr><td>100</td><td>PrepareFry</td><td>0</td><td>1</td><t" +
                "d>12</td><td>60</td></tr><tr><td>101</td><td>Turnonb" +
                "urner(low)</td><td>100</t" +
                "d><td>1</td><td>1</td><td>10</td></tr><tr><td>102</td><td>Break" +
                "eggsandpourintofry</td><td>100</td><td>2</td><t" +
                "d>4</td><td>10</td></tr><tr><td>103</td><td>Steer" +
                "mixturetoavoidsticking</td><td>100</td><td>5</td><td>10</t" +
                "d><td>10</td></tr><tr><td>105</td><td>Salt,pepper</td><td>" +
                "100</td><td>5</td><td>5</td><td>10</td></tr><tr>" +
                "<td>104</td><td>Throwyellowcheeseintofry</td><td>100" +
                "</td><td>6</td><td>12</td><td>10</td></tr><tr><td>106</td><td>Turn" +
                "burneroff</td><td>100</td><td>12</td><td>12</td><td>10</td></tr><tr><td>200</td><td>Prepare" +
                "thebread</td><td>0</td><td>10</td><td>12</td><td>20</td></tr><tr><td>201</td><td>Heatbreadin" +
                "toaster</td><td>200</td><td>10</td><td>12</td><td>10</td></tr><tr><td>202</td><td>Littlebitof" +
                "salt,galricspicetobread</td><td>200</td><td>12</td><td>12</td><td>10</td></tr><tr><td>300</td>" +
                "<td>Serveeggs</td><td>0</td><td>13</td><td>20</td><td>30</td></tr><tr><td>301</td><td>Putbreadin" +
                "plate</td><td>300</td><td>13</td><td>13</td><td>10</td></tr><tr><td>302</td><td>Puteggsonbread" +
                "</td><td>300</td><td>14</td><td>14</td><td>10</td></tr><tr><td>303</td><td>Washfry</td><td>300</td>" +
                "<td>15</td><td>20</td><td>10</td></tr></table></body></html>";
        return str;
    }

    private ArrayList<Task> getList() {
        TsvParser parser = new TsvParser();
        return parser.createTasksFromStrings(calcStrings());
    }

    private ArrayList<String[]> calcStrings() {
        String arr[][] = {
                { "100", "Prepare Fry", "0", "1", "12", "60" },
                { "101", "Turn on burner (low)", "100", "1", "1", "10" },
                { "102", "Break eggs and pour into fry", "100", "2", "4", "10" },
                { "103", "Steer mixture to avoid sticking", "100", "5", "10", "10" },
                { "105", "Salt, pepper", "100", "5", "5", "10" },
                { "104", "Throw yellow cheese into fry", "100", "6", "12", "10" },
                { "106", "Turn burner off", "100", "12", "12", "10" },
                { "200", "Prepare the bread", "0", "10", "12", "20" },
                { "201", "Heat bread in toaster", "200", "10", "12", "10" },
                { "202", "Little bit of salt, galric spice to bread", "200", "12", "12", "10" },
                { "300", "Serve eggs", "0", "13", "20", "30" },
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

    private String readFileToString(String path) {
        String str = "";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                str += line;
                line = reader.readLine();
            }
            reader.close();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        str = str.replaceAll("\t", "");
        str = str.replaceAll(" ", "");
        return str;
    }
}
