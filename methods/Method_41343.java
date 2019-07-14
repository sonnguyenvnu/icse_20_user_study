protected void addDefaultTriggerPersistenceDelegates(){
  addTriggerPersistenceDelegate(new SimpleTriggerPersistenceDelegate());
  addTriggerPersistenceDelegate(new CronTriggerPersistenceDelegate());
  addTriggerPersistenceDelegate(new CalendarIntervalTriggerPersistenceDelegate());
  addTriggerPersistenceDelegate(new DailyTimeIntervalTriggerPersistenceDelegate());
}
