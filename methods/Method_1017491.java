@Provides @Singleton public List<DatabaseManager.Table> tables(TaskTable taskTable){
  return Collections.unmodifiableList(Arrays.asList(taskTable));
}
