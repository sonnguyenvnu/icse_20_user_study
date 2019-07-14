public Date getTimestampCreated(){
  return new Date(timestampCreated.scaleByPowerOfTen(3).longValue());
}
