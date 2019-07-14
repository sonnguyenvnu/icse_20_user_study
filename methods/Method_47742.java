public void onShowReminder(@NonNull Habit habit,Timestamp timestamp,long reminderTime){
  notificationTray.show(habit,timestamp,reminderTime);
  reminderScheduler.scheduleAll();
}
