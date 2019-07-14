private void scheduleReminderMinutesFromNow(Habit habit,long minutes){
  reminderScheduler.scheduleMinutesFromNow(habit,minutes);
  notificationTray.cancel(habit);
}
