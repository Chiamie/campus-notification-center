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


String output = subscribeToTopic(students, 
"4", "Fees");
System.out.println(output);

System.out.println(changeSubscription(topics, "Exams", "4", students));

System.out.println(isTopicValid(topics, "Exams"));

String newTopic = "Emergency";

System.out.println(changeSubscription(topics, newTopic, 
"4", students));


System.out.println(hasSubscribedTo("Fees", "4", 
students));


System.out.println(unsubscribeTopic("Feyi", topics,
"4",  students));

System.out.println(unsubscribeTopic("Emergency", topics,
"4",  students));


System.out.println(unsubscribeTopic("Fees", topics,
"4",  students));

System.out.println(Arrays.toString(getStudentSubscribedTopics("4",
students)));


}



public static String unsubscribeTopic(String topic, String[] topics,
String studentId, String[][][] students) {

String[] updatedSubscribedTopics;

if(!isTopicValid(topics, topic)) 
return "You cannot unsubscribe from " + topic + "!";

if(hasSubscribedTo(topic, studentId, students)){
String[] subscribedTopics =  getStudentSubscribedTopics(studentId, students);

updatedSubscribedTopics = new String[subscribedTopics.length - 1];

for(int count = 0; count < subscribedTopics.length; count++){
if(!topic.equals(subscribedTopics[count]))
updatedSubscribedTopics[count] = subscribedTopics[count];
}
updateStudentSubscribedTopics(studentId, updatedSubscribedTopics, 
students);

}
return "You've successfully unsunscribed from " + topic + "!";
}


private static String[] updateStudentSubscribedTopics(String studentId, 
String[] updatedSubscribedTopics, String[][][] students) {

for(int index = 0; index < students.length; index++){

if(studentId.equals(students[index][0][0])) 
students[index][3] = updatedSubscribedTopics;
}
return updatedSubscribedTopics;
}

public static String[] getStudentSubscribedTopics(String studentId,
String[][][] students){

String[] subscribedTopics = {};
for(int index = 0; index < students.length; index++){
if(studentId.equals(students[index][0][0])){
subscribedTopics = students[index][3];
break;
}
}
return subscribedTopics;
}


public static String changeSubscription(String[] topics, String newTopic, String studentId, String[][][] students){

boolean isValid = isTopicValid(topics, newTopic);

if(!isTopicValid(topics, newTopic))
return "You cannot subscribe to " + newTopic + "!";

if(hasSubscribedTo(newTopic, studentId, students))
return "You've already subscribed to " + newTopic + "!";

return subscribeToTopic(students, studentId, newTopic);

}



public static boolean hasSubscribedTo(String newTopic, String studentId,
String[][][] students){

String[] subscribedTopics;

for(int index = 0; index < students.length; index++){
if(studentId.equals(students[index][0][0])){
subscribedTopics = students[index][3];

for(int count = 0; count < subscribedTopics.length; count++){
if(newTopic.equals(subscribedTopics[count]))
return true;
}

break;
}
}
return false;
}

public static boolean isTopicValid(String[] topics, String newTopic) {

if(newTopic.equals("Emergency"))
return false;

for(int index = 0; index < topics.length; index++){
if(newTopic.equals(topics[index])) return true;
}

return false;
}


public static String subscribeToTopic(String[][][] students, 
String studentId, String newTopic) {


String[] newListOfSubscribedTopics = {};
for(int index = 0; index < students.length; index++){


if(studentId.equals(students[index][0][0])) {
String studentSubscribedTopics = String.join(", ", students[index][3]);
studentSubscribedTopics += ", " + newTopic;
newListOfSubscribedTopics = studentSubscribedTopics.split(", ");
students[index][3] = newListOfSubscribedTopics;
break;
}


}
return "Updated Topics subscription to " + String.join(", ", newListOfSubscribedTopics);


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