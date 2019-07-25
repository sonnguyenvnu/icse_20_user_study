private void success(ScheduleSetRecord record){
  facade.appendDispatchLog(new DispatchLogRecord(record.getSubject(),record.getMessageId(),record.getScheduleTime(),record.getSequence()));
}
