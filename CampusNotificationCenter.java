import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;


public class CampusNotificationCenter {

public static void main(String[] args){


String[] topics = {"Fees", "Exams", "Assignments", "Events", "Emergency"};
String[] channels = {"SMS", "Email", "App"};
String[] audience = {"All", "100", "200", "300", "400"};


String[][][] students = { 
{{"1"}, {"Ada"}, {"100"}, {"Fees", "Exams", "Events"}, {"SMS", "App"}, {"Off"}},
{{"2"}, {"Tunde"}, {"200"}, {"Exams", "Assignments"}, {"Email"}, {"Off"} },
{{"3"}, {"Zainab"}, {"200"}, {"Fees", "Assignments", "Events"}, {"SMS", "Email", "App"}, {"Off"}},
{{"4"}, {"Chidi"}, {"300"}, {"Exams"}, {"App"}, {"On"}},
{{"5"}, {"Ngozi"}, {"300"}, {"Fees", "Exams", "Assignments", "Events"}, {"Email", "App"},  {"Off"}},
{{"6"}, {"Emeka"}, {"400"}, {"Assignments"}, {"SMS"}, {"Off"}}

};

//String[] notificationColumns = new String[4];
ArrayList<String[]> notifications = new ArrayList<>();
//ArrayList<String>[] studentsInbox = new ArrayList[students.length];
HashMap<String, ArrayList<String>> studentsInbox = new HashMap<>();
//List<String> inbox = new ArrayList<>();


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

System.out.println(publishNotification("topic", "Timetable Update",
"The Physics exam has moved to Friday at 9am in Hall B.", "5", audience,
students, notifications, studentsInbox));


System.out.println("Students in 200 level are : " + Arrays.toString
(filterStudentsByAudience("200", audience, students)));
System.out.println("Students in 400 level are : " + Arrays.toString
(filterStudentsByAudience("400", audience, students)));

System.out.println("Students subscribed to Events are : " + Arrays.toString(
getTopicSubscribers("Events", students)));
System.out.println("Students subscribed to Exams are : " + Arrays.toString(
getTopicSubscribers("Exams", students)));

//System.out.println("here: " + getTopicSubscribers("Events",
//students));



System.out.println(Arrays.toString(getNonDndStudents(students)));
//System.out.println(publishNonEmergencyTopics("Assignments", "Assignment 101",
//"To be submiited tomorrow", "200", audience,
//students, notifications));

String[] studentsByLevelAndTopic = {"2", "3"};
String[] activeDndStudents = getNonDndStudents(students);
System.out.println(Arrays.toString(activeDndStudents));
System.out.println(Arrays.toString(getActiveTopicSubscribersByLevel(studentsByLevelAndTopic,
activeDndStudents)));


//System.out.println("Here: " + getActiveTopicSubscribersByLevel(studentsByLevelAndTopic,
//activeDndStudents));

String[] activeTopicSubscribersByLevel = {"2", "3"};
System.out.println(Arrays.deepToString(getChannelsToPublishTo(activeTopicSubscribersByLevel,
students)));

System.out.println("Unpublished: " + publishNonEmergencyTopics("Assignments", "Assignment 101",
"To be submiited tomorrow", "200", audience,
students, notifications));

String[][] channelsToPublishTo = {{"Email"}, {"SMS", "Email", "App"}};
System.out.println(convertToString(channelsToPublishTo));


System.out.println(publishNonEmergencyTopicsOne("Assignments", "Assignment 101",
"To be submiited tomorrow", "200", audience,
students, notifications, studentsInbox));


System.out.println(publishNonEmergencyTopicsOne("Exams", "Timetable Update",
"The Physics exam has moved to Friday at 9am in Hall B.", "All", audience,
students, notifications, studentsInbox));


String message = """
Because these are zero-width assertions, they split the string
between the groups without consuming or deleting the parentheses themselves,
Because these are zero-width assertions, they split the string between,
the groups without consuming or deleting the parentheses themselves.
""";

System.out.println(shortenMessage(message));

//{Ada=[The Physics exam has moved to Friday ...
//[ALERT] Timetable Update
//]}

//System.out.println(sendNotificationToStudentInbox("1", "Exams", "Timetable Update",
//"The Physics exam has moved to Friday at 9am in Hall B.", studentsInbox, students, studentsInbox));

System.out.println(studentsInbox);

System.out.println("Look1: " + viewInboxOf("1", students,
studentsInbox));


System.out.println("Look2: " + viewInboxOf("2", students,
studentsInbox));

}


public static String viewInboxOf(String studentId, String[][][] students,
HashMap<String, ArrayList<String>> studentsInbox){

String studentName = students[Integer.parseInt(studentId) - 1][1][0];
String inboxHeader =  createInboxHeader(studentName);
ArrayList<String> studentInbox = studentsInbox.get(studentName);

//String channelBoundaryRegex = "(?=(?:\\[ALERT\\]\\s*)?\\[(?:SMS|Email|App)\\s*\\|)";
//String channelBoundaryRegex = "(?=(?:\\[ALERT\\]|\\[(?:SMS|Email)\\b|Subject:\\s*\\[Email\\b))";
String channelBoundaryRegex = "(?=(?:\\[(?:App|SMS)\\b|Subject:))";
if(studentsInbox.containsKey(studentName)) {
int itemNumber = 1;

for(String studentInboxEntry : studentInbox){
String[] channelItems = studentInboxEntry.split(channelBoundaryRegex);
for(String item : channelItems){
String cleaned = item.trim();
if(!cleaned.isEmpty())
inboxHeader += itemNumber++ + ". " + cleaned + System.lineSeparator();

}
}

return inboxHeader;
}

return "Inbox is empty!";


} 

private static String createInboxHeader(String studentName){

String viewInboxFormat = "--- Inbox for %s ---%n";
return String.format(viewInboxFormat, studentName);

}

public static String publishNotification(String topic, String title,
String message, String audienceToPublishTo, String[] audience,
String[][][] students, ArrayList<String[]> notifications,
HashMap<String, ArrayList<String>> studentsInbox) {

if(message.isBlank())
return "message must not be empty!";

if(title.length() > 30)
return "title must not exceed 30 characters!";

if(!isAudienceValid(audienceToPublishTo, 
audience)) return "Unknown audience!";

return publishNonEmergencyTopicsOne(topic, title,
message, audienceToPublishTo, audience,
students, notifications, studentsInbox);

}


private static String publishNonEmergencyTopics(String topic, String title,
String message, String audienceToPublishTo, String[] audience,
String[][][] students, ArrayList<String[]> notifications){


String[] studentsInSameLevel = filterStudentsByAudience(audienceToPublishTo,
audience, students);
String[] topicSubscribers = getTopicSubscribers(topic,
students);
String[] activeDndStudents = getNonDndStudents(students);

String[] studentsByLevelAndTopic = filterStudentsByLevelAndTopic(studentsInSameLevel,
topicSubscribers);

String[] activeTopicSubscribersByLevel = getActiveTopicSubscribersByLevel(studentsByLevelAndTopic,
activeDndStudents);

String[][] channelsToPublishTo = getChannelsToPublishTo(activeTopicSubscribersByLevel,
students);


String unpublishedStudents = getUnpublishedStudents(activeTopicSubscribersByLevel,
students);


String inactive =  getInactiveTopicSubscribersByLevel(studentsByLevelAndTopic,
activeTopicSubscribersByLevel);
//return String.join(", ", studentsByLevelAndTopic);
//return unpublishedStudents;
return inactive;
}


private static String publishNonEmergencyTopicsOne(String topic, String title,
String message, String audienceToPublishTo, String[] audience,
String[][][] students, ArrayList<String[]> notifications,
HashMap<String, ArrayList<String>> studentsInbox){


String[] studentsInSameLevel = filterStudentsByAudience(audienceToPublishTo,
audience, students);
String[] topicSubscribers = getTopicSubscribers(topic,
students);
String[] activeDndStudents = getNonDndStudents(students);

String[] studentsByLevelAndTopic = filterStudentsByLevelAndTopic(studentsInSameLevel,
topicSubscribers);

String[] activeTopicSubscribersByLevel = getActiveTopicSubscribersByLevel(studentsByLevelAndTopic,
activeDndStudents);

String[][] channelsToPublishTo = getChannelsToPublishTo(activeTopicSubscribersByLevel,
students);
 
String notificationsReportEntry = generateNotificationReportEntry(channelsToPublishTo, notifications, students, 
activeTopicSubscribersByLevel, studentsByLevelAndTopic);

String output = "";
for(int index = 0; index < activeTopicSubscribersByLevel.length; index++){

output = sendNotificationToStudentInbox(activeTopicSubscribersByLevel[index], 
topic, title, message, studentsInbox, students, notificationsReportEntry);


}


return notificationsReportEntry;
}



private static String sendNotificationToStudentInbox(String studentId, String topic, String title,
String message, HashMap<String, ArrayList<String>>studentsInbox,
String[][][] students, String notificationsReportEntry){

ArrayList<String> inbox = new ArrayList<>();
String inboxContent = createInboxFormat(studentId, topic, title,
message, students, notificationsReportEntry);
inbox.add(inboxContent);
studentsInbox.put(students[Integer.parseInt(studentId) - 1][1][0], inbox);

return inboxContent;

}


private static String createInboxFormat(String studentId, String topic, String title,
String message, String[][][] students, String notificationsReportEntry){

String[] studentEnabledChannels = getStudentEnabledChannels(studentId, 
students);

String inboxContent = "";

for(int index = 0; index < studentEnabledChannels.length; index++){

if(studentEnabledChannels[index].equals("SMS")){

if(message.length() > 40)
message = shortenMessage(message);

String messageFormat = "%s%s%n";
String messageSent = String.format(messageFormat, "[SMS | " + topic + " | " + notificationsReportEntry.substring(13, 15) + "] ",
message);
inboxContent += messageSent;
}

if(studentEnabledChannels[index].equals("Email")){

String emailFormat = "Subject: %s%nBody: %s%n";
String mail = String.format(emailFormat, "[Email | " + topic + " | " + notificationsReportEntry.substring(13, 15) + "] ",
title, message);
inboxContent += mail;
}


if(studentEnabledChannels[index].equals("App")){

String appFormat = "%s%s [ALERT] %n";
String app = String.format(appFormat, "[App | " + topic + " | " + notificationsReportEntry.substring(13, 15) + "] ",
title);
inboxContent += app;
}

}
return inboxContent;


}





private static String shortenMessage(String message){

String shortenedMessage = message.substring(0, 37);
return shortenedMessage += "...";
}




private static String generateNotificationReportEntry(String[][] channelsToPublishTo,
ArrayList<String[]> notifications, String[][][] students,
String[] activeTopicSubscribersByLevel, String[] studentsByLevelAndTopic){

String[] notificationsColumn = new String[5];

String notificationCount = "0";
if(!notifications.isEmpty())
notificationCount = (notifications.get(notifications.size() - 1)[0]);


for(int counter = 0; counter < notificationsColumn.length; counter++){

switch(counter) {
case 0 -> notificationsColumn[counter] = Integer.toString(
(Integer.parseInt(notificationCount) + 1));
case 1 -> notificationsColumn[counter] = 
String.join(", ", getPublishedStudentsName(activeTopicSubscribersByLevel,
students));
case 2 -> notificationsColumn[counter] = convertToString(channelsToPublishTo);
//Arrays.deepToString(channelsToPublishTo);
case 3 -> notificationsColumn[counter] = 
getInactiveTopicSubscribersByLevel(studentsByLevelAndTopic, activeTopicSubscribersByLevel);
case 4 -> notificationsColumn[counter] = getUnpublishedStudents(activeTopicSubscribersByLevel,
students);
}
}

notifications.add(notificationsColumn);


String deliveredContent = formatDeliveryDetails(notificationsColumn);
String heldContent = formatHeldDndDetails(notificationsColumn, students);
String notificationReportFormat = "Notification #%s published%nDelivered: %s%nHeld (DND): %s%nNot subsrcibed / not in audience: %s%n";


String notificationReportEntry = String.format(notificationReportFormat,
notificationsColumn[0], deliveredContent, heldContent,
notificationsColumn[4]);

return notificationReportEntry;
}


private static String formatHeldDndDetails(String[] notificationsColumn,
String[][][] students) {

if(notificationsColumn[3]==null)
return "No inactive topic subscribers by this level";

String[] studentsHeld = notificationsColumn[3].split(", ");
String heldStudents = "";
for(int index = 0; index < studentsHeld.length; index++){

heldStudents += getStudentName(studentsHeld[index], students) + ", ";
}
return heldStudents;

}

private static String formatDeliveryDetails(String[] notificationsColumn) {

String deliveredContent = "";

String[] elementTwo = notificationsColumn[1].split(", ");
String[] elementThree = notificationsColumn[2].split("(?<=\\))\\s*(?=\\()");

for(int index = 0; index < elementTwo.length; index++){
deliveredContent += elementTwo[index];
deliveredContent += elementThree[index];
deliveredContent += ", ";
}

return deliveredContent;

}

private static String getUnpublishedStudents(String[] activeTopicSubscribersByLevel,
String[][][] students) {

String[] publishedStudentsName = getPublishedStudentsName(activeTopicSubscribersByLevel, students);

String[] nameOfStudents = getAllStudentsName(students);

Set<String> allStudents = new HashSet<>(Arrays.asList(nameOfStudents));
Set<String> publishedStudentsNameSet = new HashSet<>(Arrays.asList(publishedStudentsName));


Set<String> nameOfUnpublishedStudents = new HashSet<>(allStudents);
nameOfUnpublishedStudents.removeAll(publishedStudentsNameSet);

return String.join(", ", nameOfUnpublishedStudents);
}

private static String[] getAllStudentsName(String[][][] students){
String[] nameOfStudents = new String[students.length];
for(int index = 0; index < students.length; index++){
nameOfStudents[index] = students[index][1][0];
}

return nameOfStudents;
}

private static String getStudentName(String studentId, String[][][] students) {

String[] nameOfStudents = getAllStudentsName(students);
int index = Integer.parseInt(studentId) - 1;
return nameOfStudents[index];

}

private static String getInactiveTopicSubscribersByLevel(String[] studentsByLevelAndTopic,
String[] activeTopicSubscribersByLevel){


Set<String> setOfStudentsByLevelAndTopic = new HashSet<>(Arrays.asList(studentsByLevelAndTopic));
Set<String> setOfActiveTopicSubscribersByLevel = new HashSet<>(Arrays.asList(activeTopicSubscribersByLevel));


Set<String> setOfInactiveTopicSubscribersByLevel = new HashSet<>(setOfStudentsByLevelAndTopic);
setOfInactiveTopicSubscribersByLevel.removeAll(setOfActiveTopicSubscribersByLevel);

if(setOfInactiveTopicSubscribersByLevel.isEmpty()) return null;

return String.join(", ", setOfInactiveTopicSubscribersByLevel);

}

private static String convertToString(String[][] channelsToPublishTo){

String stringFormat = "";
for(int index = 0; index < channelsToPublishTo.length; index++){
String[] studentEnabledChannels = channelsToPublishTo[index];
String channels = "(";
channels += String.join(", ", studentEnabledChannels);
channels += ")";
stringFormat += channels;
}
return stringFormat;

}

private static String[] getPublishedStudentsName(String[] activeTopicSubscribersByLevel,
String[][][] students){

String[] publishedStudentsName = new String[activeTopicSubscribersByLevel.length];

for(int counter = 0; counter < activeTopicSubscribersByLevel.length; counter++){
if(activeTopicSubscribersByLevel[counter].equals
(students[Integer.parseInt(activeTopicSubscribersByLevel[counter]) - 1][0][0]));
publishedStudentsName[counter] = 
(students[Integer.parseInt(activeTopicSubscribersByLevel[counter]) - 1][1][0]);


}
return publishedStudentsName;
}

private static String[][] getChannelsToPublishTo(String[] activeTopicSubscribersByLevel,
String[][][] students){

String[][] channelsToPublishTo =  new String[activeTopicSubscribersByLevel.length][];
for(int index = 0; index < activeTopicSubscribersByLevel.length; index++){
String[] studentEnabledChannels = getStudentEnabledChannels(activeTopicSubscribersByLevel[index], students);
channelsToPublishTo[index] = studentEnabledChannels;
}

return channelsToPublishTo;
}

private static String[] getActiveTopicSubscribersByLevel(String[] studentsByLevelAndTopic,
String[] activeDndStudents) {

String activeTopicSubscribersByLevel = "";
for(int index = 0; index < studentsByLevelAndTopic.length; index++){
for(int counter = 0; counter < activeDndStudents.length; counter++){
if(studentsByLevelAndTopic[index].equals(activeDndStudents[counter])){
activeTopicSubscribersByLevel += studentsByLevelAndTopic[index] + ", ";
break;
}

}
}

return activeTopicSubscribersByLevel.split(", ");
//return activeTopicSubscribersByLevel;
}


private static String[] filterStudentsByLevelAndTopic(String[] studentsInSameLevel,
String[] topicSubscribers){

String studentsInSameLevelAndTopics = "";

for(int index = 0; index < studentsInSameLevel.length; index++){
for(int counter = 0; counter < topicSubscribers.length; counter++){
if(studentsInSameLevel[index].equals(topicSubscribers[counter])){
studentsInSameLevelAndTopics += studentsInSameLevel[index] + ", ";
break;
}
}
}

return studentsInSameLevelAndTopics.split(", ");
}



private static String[] getNonDndStudents(String[][][] students){

String activeDndStudents = "";
for(int index = 0; index < students.length; index++){
if(students[index][5][0].equals("Off"))
activeDndStudents += students[index][0][0] + ", ";
}

return activeDndStudents.split(", ");
}

private static String[] getTopicSubscribers(String topic,
String[][][] students){

String topicSubscribers = "" ;
for(int index = 0; index < students.length; index++){
//String[] subscribedTopics = students[index][3];
if(hasSubscribedTo(topic, students[index][0][0],
students))
topicSubscribers += students[index][0][0] + ", ";
}

return topicSubscribers.split(", ");


}

private static String[] filterStudentsByAudience(String audienceToPublishTo,
String[] audience, String[][][] students){

String studentsInSameLevel = "";

if(audienceToPublishTo.equals("All")){
for(int index = 0; index < students.length; index++){
studentsInSameLevel += students[index][0][0] + ", ";
}

return studentsInSameLevel.split(", ");}

for(int index = 0; index < students.length; index++){
if(audienceToPublishTo.equals(students[index][2][0]))
studentsInSameLevel += students[index][0][0] + ", ";
}
return studentsInSameLevel.split(", ");

}

private static boolean isAudienceValid(String audienceToPublishTo, 
String[] audience){

for(int index = 0; index < audience.length; index++){
if(audienceToPublishTo.equals(audience[index]))
return true;
}
return false;
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