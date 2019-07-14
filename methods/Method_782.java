public static Character castToChar(Object value){
  if (value == null) {
    return null;
  }
  if (value instanceof Character) {
    return (Character)value;
  }
  if (value instanceof String) {
    String strVal=(String)value;
    if (strVal.length() == 0) {
      return null;
    }
    if (strVal.length() != 1) {
      throw new JSONException("can not cast to char, value : " + value);
    }
    return strVal.charAt(0);
  }
  throw new JSONException("can not cast to char, value : " + value);
}
