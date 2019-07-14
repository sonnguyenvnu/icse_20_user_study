@Override public void storeCalendar(String name,Calendar calendar,boolean replaceExisting,boolean updateTriggers) throws JobPersistenceException {
  clusteredJobStore.storeCalendar(name,calendar,replaceExisting,updateTriggers);
}
