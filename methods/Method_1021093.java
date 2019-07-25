@Override public NakadiCursor convert(final String eventTypeStr,final Cursor cursor) throws InternalNakadiException, NoSuchEventTypeException, ServiceTemporarilyUnavailableException, InvalidCursorException {
  final String offset=cursor.getOffset();
  if (Cursor.BEFORE_OLDEST_OFFSET.equalsIgnoreCase(offset)) {
    final Timeline timeline=timelineService.getActiveTimelinesOrdered(eventTypeStr).get(0);
    return timelineService.getTopicRepository(timeline).loadPartitionStatistics(timeline,cursor.getPartition()).orElseThrow(() -> new InvalidCursorException(PARTITION_NOT_FOUND)).getBeforeFirst();
  }
 else   if (!NUMBERS_ONLY_PATTERN.matcher(offset).matches()) {
    throw new InvalidCursorException(CursorError.INVALID_OFFSET,cursor);
  }
  final Timeline timeline=timelineService.getAllTimelinesOrdered(eventTypeStr).get(0);
  if (offset.startsWith("-")) {
    return NakadiCursor.of(timeline,cursor.getPartition(),cursor.getOffset());
  }
 else {
    return NakadiCursor.of(timeline,cursor.getPartition(),StringUtils.leftPad(cursor.getOffset(),VERSION_ZERO_MIN_OFFSET_LENGTH,'0'));
  }
}
