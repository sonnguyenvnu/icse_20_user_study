private int migrateAll(){
  int total=0;
  while (true) {
    final boolean firstRun=total == 0;
    int count=configuration.isGroup() ? migrateGroup(firstRun) : schemaHistory.lock(new Callable<Integer>(){
      @Override public Integer call(){
        return migrateGroup(firstRun);
      }
    }
);
    total+=count;
    if (count == 0) {
      break;
    }
  }
  return total;
}
