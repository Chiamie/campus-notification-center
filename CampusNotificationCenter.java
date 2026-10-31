import java.util.Arrays;
public class CampusNotificationCenter {

public static void main(String[] args){


String[] topics = {"Fees", "Exams", "Assignments", "Events", "Emergency"};
String[] channels = {"SMS", "Email", "App"};

String[][][] students = { 
{{"1"}, {"Ada"}, {"100"}, {"Fees", "Exams", "Events"}, {"SMS", "App"}, {"Off"}},
{{"2"}, {"Tunde"}, {"200"}, {"Exams", "Assignments"}, {"Email"}, {"Off"} },
{{"3"}, {"Zainab"}, {"200"}, {"Fees", "Assignments", "Events"}, {"SMS", "Email", "App"}, {"Off"}},
{{"4"}, {"Chidi"}, {"300"}, {"Exams"}, {"App"}, {"On"}},
{{"5"}, {"Ngozi"}, {"300"}, {"Fees", "Exams", "Assignments", "Events"}, {"Email", "App"},  {"Off"}},
{{"6"}, {"Emeka"}, {"400"}, {"Assignments"}, {"SMS"}, {"Off"}}


};

String studentTable = viewStudents(students);
System.out.print(studentTable);














}

public static String getTableFormat(){

String output = "";
String headerFormat = "%-6s %-10s %-8s %-35s %-25s %-5s%n";
output += String.format(headerFormat, "ID", "Name", "Level", 
"Subscribed Topics", "Enabled Channels", "DND");
output += "-".repeat(95);
output += "\n";
return output;
}

public static String viewStudents(String[][][] students) {

String output = getTableFormat();
String headerFormat = "%-6s %-10s %-8s %-35s %-25s %-5s%n";

for(int index = 0; index < students.length; index++){
String id = students[index][0][0];
String name = students[index][1][0];
String level = students[index][2][0];

String subscribedTopics = String.join(", ", students[index][3]);
String enabledChannels = String.join(", ", students[index][4]);
String dnd = students[index][5][0];

String row = String.format(headerFormat, id, name, level, subscribedTopics, enabledChannels, dnd);
output += row;

}
return output;
}




























}