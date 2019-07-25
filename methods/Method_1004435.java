ScheduleSetRecord recover(long scheduleTime,int size,long offset){
  ScheduleSetSegment segment=(ScheduleSetSegment)locateSegment(scheduleTime);
  if (segment == null) {
    LOGGER.error("schedule set recover null value, scheduleTime:{}, size:{}, offset:{}",scheduleTime,size,offset);
    return null;
  }
  return segment.recover(offset,size);
}
