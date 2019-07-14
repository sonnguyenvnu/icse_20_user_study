private long tilNextMillis(){
  long timestamp=getTimeMill();
  while (timestamp <= lastStamp) {
    timestamp=getTimeMill();
  }
  return timestamp;
}
