/** 
 * Retrieves the next major release to be expected. Useful when logging deprecation messages to indicate when support will be removed.
 * @return The next major release to be expected.
 */
public static String getNextMajorRelease(){
  if (isUnknown()) {
    return UNKNOWN_VERSION;
  }
  final int major=Integer.parseInt(VERSION.split("\\.")[0]);
  return (major + 1) + ".0.0";
}
