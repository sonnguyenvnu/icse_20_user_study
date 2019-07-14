@Override public synchronized void importHabitsFromFile(@NonNull File file) throws IOException {
  Database db=opener.open(file);
  MigrationHelper helper=new MigrationHelper(db);
  helper.migrateTo(DATABASE_VERSION);
  Repository<HabitRecord> habitsRepository;
  Repository<RepetitionRecord> repsRepository;
  habitsRepository=new Repository<>(HabitRecord.class,db);
  repsRepository=new Repository<>(RepetitionRecord.class,db);
  for (  HabitRecord habitRecord : habitsRepository.findAll("order by position")) {
    Habit h=modelFactory.buildHabit();
    habitRecord.copyTo(h);
    h.setId(null);
    habitList.add(h);
    List<RepetitionRecord> reps=repsRepository.findAll("where habit = ?",habitRecord.id.toString());
    for (    RepetitionRecord r : reps)     h.getRepetitions().toggle(new Timestamp(r.timestamp),r.value);
  }
}
