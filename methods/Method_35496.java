/** 
 * Try to convert a string into a number, boolean, or null. If the string can't be converted, return the string.
 * @param string A String. can not be null.
 * @return A simple JSON value.
 * @throws NullPointerException Thrown if the string is null.
 */
public static Object stringToValue(String string){
  if ("".equals(string)) {
    return string;
  }
  if ("true".equalsIgnoreCase(string)) {
    return Boolean.TRUE;
  }
  if ("false".equalsIgnoreCase(string)) {
    return Boolean.FALSE;
  }
  if ("null".equalsIgnoreCase(string)) {
    return JSONObject.NULL;
  }
  char initial=string.charAt(0);
  if ((initial >= '0' && initial <= '9') || initial == '-') {
    try {
      if (isDecimalNotation(string)) {
        Double d=Double.valueOf(string);
        if (!d.isInfinite() && !d.isNaN()) {
          return d;
        }
      }
 else {
        Long myLong=Long.valueOf(string);
        if (string.equals(myLong.toString())) {
          if (myLong.longValue() == myLong.intValue()) {
            return Integer.valueOf(myLong.intValue());
          }
          return myLong;
        }
      }
    }
 catch (    Exception ignore) {
    }
  }
  return string;
}
