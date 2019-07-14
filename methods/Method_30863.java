protected String formatTime(ZonedDateTime time){
  return TimeUtils.formatDateTime(time,getContext());
}
