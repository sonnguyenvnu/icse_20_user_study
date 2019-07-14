private void loadRecords(){
  if (loaded)   return;
  loaded=true;
  list.removeAll();
  List<HabitRecord> records=repository.findAll("order by position");
  for (  HabitRecord rec : records) {
    Habit h=modelFactory.buildHabit();
    rec.copyTo(h);
    list.add(h);
  }
}
