/** 
 * Returns a boolean indicating whether or not a DateTime value is the Epoch. Returns `true` if the DateTime equals 1970-01-01T00:00:00Z.
 */
public static boolean isEpoch(final @NonNull DateTime dateTime){
  return dateTime.getMillis() == 0;
}
