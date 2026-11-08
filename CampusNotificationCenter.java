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


System.out.println(isChannelValid("SMS", channels));
System.out.println(isChannelValid("smart", channels));

System.out.println("Here" + isChannelValid("Events", channels));


System.out.println(Arrays.toString(getStudentEnabledChannels("4", students)));

System.out.println(isChannelEnabled("SMS", channels, "4", students));
System.out.println(isChannelEnabled("App", channels, "4", students));

System.out.println(enableChannel("4", "nta", channels, students));
System.out.println(enableChannel("4", "App", channels, students));
System.out.println(disableChannel("4", "App", channels, students));
System.out.println(enableChannel("4", "SMS", channels, students));

System.out.println(isEnabledChannelsSizeOneFor("4", students));
System.out.println(disableChannel("4", "Email", channels, students));
System.out.println(disableChannel("4", "App", channels, students));

updateStudentDND("4", students);
System.out.println(getStudentDND("4", students));
System.out.println(toggleDND("4", students));
System.out.println(getStudentDND("4", students));

}


public static String toggleDND(String studentId, String[][][] students){

updateStudentDND(studentId, students);
//return "Do not Disturb is now " + students[Integer.parseInt(studentId) - 1][5][0];
return "Do not Disturb is now " + getStudentDND(studentId, students);

}

private static void updateStudentDND(String studentId, String[][][] students){
for(int index = 0; index < students.length; index++){
if(studentId.equals(students[index][0][0]) && students[index][5][0].equals("On"))
students[index][5][0] = "Off";

else if(studentId.equals(students[index][0][0]) && students[index][5][0].equals("Off") )
students[index][5][0] = "On";
}
}

private static String getStudentDND(String studentId, String[][][] students){
for(int index = 0; index < students.length; index++){
if(studentId.equals(students[index][0][0]))
return students[index][5][0];

}
return studentId + "not found";

}
































public static String disableChannel(String studentId, String newChannel,
String[] channels, String[][][] students) {

if(isChannelValid(newChannel, channels)){
if(!isChannelEnabled(newChannel, channels, 
studentId, students))
return newChannel + " is not enabled!";
} else return newChannel + " is not a valid channel!";

if(isEnabledChannelsSizeOneFor(studentId, students)) 
return newChannel + " cannot be disabled!";

String[] studentEnabledChannels = getStudentEnabledChannels(studentId, students);
String[] updatedStudentEnabledChannels = new String[studentEnabledChannels.length - 1];
int newIndex = 0;
for(int index = 0; index < studentEnabledChannels.length; index++){
if(!studentEnabledChannels[index].equals(newChannel)){
updatedStudentEnabledChannels[newIndex] = studentEnabledChannels[index];
newIndex++;
}
}
updateChannels(studentId, updatedStudentEnabledChannels, students);
return newChannel + " disabled successfully! Your enabled channel: " + 
String.join(",", students[Integer.parseInt(studentId) - 1][4]);
}

private static boolean isEnabledChannelsSizeOneFor(String studentId,
String[][][] students){

String[] studentEnabledChannels = getStudentEnabledChannels(studentId, students);
if(studentEnabledChannels.length == 1) return true;
return false;

}



public static String enableChannel(String studentId, String newChannel,
String[] channels, String[][][] students) {

if(isChannelValid(newChannel, channels)){
if(isChannelEnabled(newChannel, channels, 
studentId, students)) 
return newChannel + " is already enabled!";

} else return newChannel + " not an available channel!";

String[] studentEnabledChannels = getStudentEnabledChannels(studentId, students);

String[] updatedStudentEnabledChannels = Arrays.copyOf(getStudentEnabledChannels(studentId, students), 
studentEnabledChannels.length + 1);

updatedStudentEnabledChannels[studentEnabledChannels.length] = newChannel;
updateChannels(studentId, updatedStudentEnabledChannels, students);
return newChannel + " enabled successfully! These are your enabled channels " + 
String.join(", ", updatedStudentEnabledChannels);

}

private static String[][][] updateChannels(String studentId, 
String[] updatedStudentEnabledChannels, String[][][] students) {

for(int index = 0; index < students.length; index++){
if(studentId.equals(students[index][0][0])) {
students[index][4] = updatedStudentEnabledChannels;
return students;
}
}
return null;
}

private static boolean isChannelEnabled(String newChannel, String[] channels, 
String studentId, String[][][] students) {
String[] studentEnabledChannels = getStudentEnabledChannels(studentId, students);
for(String channel : studentEnabledChannels) {
if(newChannel.equals(channel)) return true;
}
return false;
}


private static String[] getStudentEnabledChannels(String studentId, 
String[][][] students){


for(int index = 0; index < students.length; index++){
if(studentId.equals(students[index][0][0]))
return students[index][4];
}
return new String[0];
}


private static boolean isChannelValid(String newChannel, String[] channels) {

for(int index = 0; index < channels.length; index++){
if(newChannel.equals(channels[index])) return true;
}
return false;
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