public Date getTimestamp(){
  return new Date(timestamp.scaleByPowerOfTen(3).longValue());
}
