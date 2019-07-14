private void createHabits(Database db){
  Cursor c=null;
  try {
    c=db.query("select _id, name, description, schedule, " + "active_days, repeating_count, days, period " + "from habits");
    if (!c.moveToNext())     return;
    do {
      int id=c.getInt(0);
      String name=c.getString(1);
      String description=c.getString(2);
      int schedule=c.getInt(3);
      String activeDays=c.getString(4);
      int repeatingCount=c.getInt(5);
      int days=c.getInt(6);
      int periodIndex=c.getInt(7);
      Habit habit=modelFactory.buildHabit();
      habit.setName(name);
      habit.setDescription(description);
      int periods[]={7,31,365};
      int numerator, denominator;
switch (schedule) {
case 0:
        numerator=activeDays.split(",").length;
      denominator=7;
    break;
case 1:
  numerator=days;
denominator=(periods[periodIndex]);
break;
case 2:
numerator=1;
denominator=repeatingCount;
break;
default :
throw new IllegalStateException();
}
habit.setFrequency(new Frequency(numerator,denominator));
habitList.add(habit);
createReminder(db,habit,id);
createCheckmarks(db,habit,id);
}
 while (c.moveToNext());
}
  finally {
if (c != null) c.close();
}
}
