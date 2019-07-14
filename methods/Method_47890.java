@Override public synchronized Iterator<Habit> iterator(){
  loadRecords();
  return list.iterator();
}
