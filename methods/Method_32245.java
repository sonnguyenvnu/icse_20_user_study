/** 
 * This method assumes that this field is properly rounded on 1970-01-01T00:00:00. If the rounding alignment differs, override this method as follows: <pre> return super.roundCeiling(instant + ALIGNMENT_MILLIS) - ALIGNMENT_MILLIS; </pre>
 */
public long roundCeiling(long instant){
  if (instant > 0) {
    instant-=1;
    return instant - instant % iUnitMillis + iUnitMillis;
  }
 else {
    return instant - instant % iUnitMillis;
  }
}
