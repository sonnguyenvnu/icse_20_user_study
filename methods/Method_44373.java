@Override public Date getEndTime(){
  return new Date(Long.valueOf(getEnd()) * 1000);
}
