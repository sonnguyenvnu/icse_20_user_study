/** 
 * Returns system property as an int.
 */
public static long getInt(final String name,final int defaultValue){
  String value=get(name);
  if (value == null) {
    return defaultValue;
  }
  value=value.trim().toLowerCase();
  try {
    return Integer.parseInt(value);
  }
 catch (  NumberFormatException nfex) {
    return defaultValue;
  }
}
