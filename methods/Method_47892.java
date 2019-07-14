private void loadRecords(){
  if (loaded)   return;
  loaded=true;
  check(habit.getId());
  List<RepetitionRecord> records=repository.findAll("where habit = ? order by timestamp",habit.getId().toString());
  for (  RepetitionRecord rec : records)   list.add(rec.toRepetition());
}
