@Override protected String formatTime(ZonedDateTime time){
  return TimeUtils.formatDate(time,getContext());
}
