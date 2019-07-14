private void createCheckmarks(@NonNull Database db,@NonNull Habit habit,int tickmateTrackId){
  Cursor c=null;
  try {
    String[] params={Integer.toString(tickmateTrackId)};
    c=db.query("select distinct year, month, day from ticks where _track_id=?",params);
    if (!c.moveToNext())     return;
    do {
      int year=c.getInt(0);
      int month=c.getInt(1);
      int day=c.getInt(2);
      GregorianCalendar cal=DateUtils.getStartOfTodayCalendar();
      cal.set(year,month,day);
      habit.getRepetitions().toggle(new Timestamp(cal));
    }
 while (c.moveToNext());
  }
  finally {
    if (c != null)     c.close();
  }
}
