public long roundCeiling(long instant){
  return super.roundCeiling(instant + 3 * DateTimeConstants.MILLIS_PER_DAY) - 3 * DateTimeConstants.MILLIS_PER_DAY;
}
