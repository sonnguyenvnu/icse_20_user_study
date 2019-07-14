@Override public Repository<HabitRecord> buildHabitListRepository(){
  return new Repository<>(HabitRecord.class,db);
}
