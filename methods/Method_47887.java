public void copyFrom(Repetition repetition){
  timestamp=repetition.getTimestamp().getUnixTime();
  value=repetition.getValue();
}
