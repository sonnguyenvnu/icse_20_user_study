private void createReminder(Database db,Habit habit,int rewireHabitId){
  String[] params={Integer.toString(rewireHabitId)};
  Cursor c=null;
  try {
    c=db.query("select time, active_days from reminders where habit_id=? limit 1",params);
    if (!c.moveToNext())     return;
    int rewireReminder=Integer.parseInt(c.getString(0));
    if (rewireReminder <= 0 || rewireReminder >= 1440)     return;
    boolean reminderDays[]=new boolean[7];
    String activeDays[]=c.getString(1).split(",");
    for (    String d : activeDays) {
      int idx=(Integer.parseInt(d) + 1) % 7;
      reminderDays[idx]=true;
    }
    int hour=rewireReminder / 60;
    int minute=rewireReminder % 60;
    WeekdayList days=new WeekdayList(reminderDays);
    Reminder reminder=new Reminder(hour,minute,days);
    habit.setReminder(reminder);
    habitList.update(habit);
  }
  finally {
    if (c != null)     c.close();
  }
}
