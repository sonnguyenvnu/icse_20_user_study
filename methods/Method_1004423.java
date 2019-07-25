private void release(List<ScheduleSetRecord> records){
  for (  ScheduleSetRecord record : records) {
    record.release();
  }
}
