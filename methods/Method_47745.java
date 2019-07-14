public void onSnoozeTimePicked(Habit habit,int hour,int minute){
  Long time=DateUtils.getUpcomingTimeInMillis(hour,minute);
  reminderScheduler.scheduleAtTime(habit,time);
  notificationTray.cancel(habit);
}
