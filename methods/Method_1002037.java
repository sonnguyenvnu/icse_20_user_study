@Override public Long from(Timestamp timestamp){
  if (timestamp == null) {
    return 0L;
  }
  return timestamp.toInstant().getEpochSecond();
}
