/** 
 * This method assumes that this field is properly rounded on 1970-01-01T00:00:00. If the rounding alignment differs, override this method as follows: <pre> return super.roundFloor(instant + ALIGNMENT_MILLIS) - ALIGNMENT_MILLIS; </pre>
 */
public long roundFloor(long instant){
  if (instant >= 0) {
    return instant - instant % iUnitMillis;
  }
 else {
    instant+=1;
    return instant - instant % iUnitMillis - iUnitMillis;
  }
}
