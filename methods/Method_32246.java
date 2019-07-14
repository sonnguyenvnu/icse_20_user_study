/** 
 * This method assumes that this field is properly rounded on 1970-01-01T00:00:00. If the rounding alignment differs, override this method as follows: <pre> return super.remainder(instant + ALIGNMENT_MILLIS); </pre>
 */
public long remainder(long instant){
  if (instant >= 0) {
    return instant % iUnitMillis;
  }
 else {
    return (instant + 1) % iUnitMillis + iUnitMillis - 1;
  }
}
