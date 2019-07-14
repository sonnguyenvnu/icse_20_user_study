/** 
 * @param instant millis from 1970-01-01T00:00:00Z
 */
int getDayOfWeek(long instant){
  long daysSince19700101;
  if (instant >= 0) {
    daysSince19700101=instant / DateTimeConstants.MILLIS_PER_DAY;
  }
 else {
    daysSince19700101=(instant - (DateTimeConstants.MILLIS_PER_DAY - 1)) / DateTimeConstants.MILLIS_PER_DAY;
    if (daysSince19700101 < -3) {
      return 7 + (int)((daysSince19700101 + 4) % 7);
    }
  }
  return 1 + (int)((daysSince19700101 + 3) % 7);
}
