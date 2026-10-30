# Campus Notification Center

A Java console application that simulates a campus notification system. An admin publishes notices, and the system delivers each one to the right students according to their topic subscriptions, channel settings and "Do Not Disturb" status.

Built for the **Native's First Phase Gate** (Semicolon Village).

## What the app does

- **Manage students:** view all students, change their topic subscriptions and channel settings, and toggle Do Not Disturb (DND).
- **Publish notifications:** choose a topic, title and message, and send to all students or to a single level (100, 200, 300 or 400).
- **Deliver by rules:** delivery depends on audience, subscription, enabled channels and DND. Emergency notices bypass all of these.
- **Hold and release:** notices for students with DND On are held and delivered when DND is switched Off.
- **Format by channel:** SMS is shortened to 40 characters, Email shows the subject and full body, and App shows an alert with the title only.
- **Inbox and summary:** view any student's inbox and a delivery summary (totals per channel and per topic, plus held notices).

## Topics and channels

| Topics | Channels |
|---|---|
| Fees, Exams, Assignments, Events, Emergency | SMS, Email, App |

## Sample session

```
=== CAMPUS NOTIFICATION CENTER ===
1. View all students           5. Publish a notification
2. Change topic subscription   6. View a student's inbox
3. Change channel settings     7. View delivery summary
4. Toggle Do Not Disturb       8. Exit
Choose: 5

Topic: Exams
Title: Timetable Update
Message: The Physics exam has moved to Friday at 9am in Hall B.
Audience (All / 100 / 200 / 300 / 400): All

Notification #1 published.
Delivered: Ada (SMS, App), Tunde (Email), Ngozi (Email, App)
Held (DND): Chidi
Not subscribed / not in audience: Zainab, Emeka
```

## Constraints

- Single `.java` file with a `Main` class and static methods only.
- Uses variables, control structures, operators, arrays, `ArrayList` and methods.
- No classes, objects, interfaces, enums or records beyond `Main`.
- Must handle invalid input without crashing.

## Running the app

```bash
javac CampusNotificationCenter.java
java CampusNotificationCenter
```

## Author

<Chiamaka Okechukwu,> · Natives · Semicolon Village
