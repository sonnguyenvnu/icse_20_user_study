@Override @SuppressWarnings("unchecked") public RecordResult<T> append(LogRecord record){
  long scheduleTime=record.getScheduleTime();
  DelaySegment<T> segment=locateSegment(scheduleTime);
  if (null == segment) {
    segment=allocNewSegment(scheduleTime);
  }
  if (null == segment) {
    return new NopeRecordResult(PutMessageStatus.CREATE_MAPPED_FILE_FAILED);
  }
  return retResult(segment.append(record,appender));
}
