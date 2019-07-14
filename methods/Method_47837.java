private void createHabits(Database db){
  Cursor c=null;
  try {
    c=db.query("select _id, name, description from tracks",new String[0]);
    if (!c.moveToNext())     return;
    do {
      int id=c.getInt(0);
      String name=c.getString(1);
      String description=c.getString(2);
      Habit habit=modelFactory.buildHabit();
      habit.setName(name);
      habit.setDescription(description);
      habit.setFrequency(Frequency.DAILY);
      habitList.add(habit);
      createCheckmarks(db,habit,id);
    }
 while (c.moveToNext());
  }
  finally {
    if (c != null)     c.close();
  }
}
