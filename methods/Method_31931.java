public long remainder(long instant){
  return super.remainder(instant + 3 * DateTimeConstants.MILLIS_PER_DAY);
}
