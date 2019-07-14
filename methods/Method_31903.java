/** 
 * @param instant millis from 1970-01-01T00:00:00Z
 */
int getMillisOfDay(long instant){
  if (instant >= 0) {
    return (int)(instant % DateTimeConstants.MILLIS_PER_DAY);
  }
 else {
    return (DateTimeConstants.MILLIS_PER_DAY - 1) + (int)((instant + 1) % DateTimeConstants.MILLIS_PER_DAY);
  }
}
