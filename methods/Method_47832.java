private void createCheckmarks(@NonNull Database db,@NonNull Habit habit,int rewireHabitId){
  Cursor c=null;
  try {
    String[] params={Integer.toString(rewireHabitId)};
    c=db.query("select distinct date from checkins where habit_id=? and type=2",params);
    if (!c.moveToNext())     return;
    do {
      String date=c.getString(0);
      int year=Integer.parseInt(date.substring(0,4));
      int month=Integer.parseInt(date.substring(4,6));
      int day=Integer.parseInt(date.substring(6,8));
      GregorianCalendar cal=DateUtils.getStartOfTodayCalendar();
      cal.set(year,month - 1,day);
      habit.getRepetitions().toggle(new Timestamp(cal));
    }
 while (c.moveToNext());
  }
  finally {
    if (c != null)     c.close();
  }
}
