public float floatValue(){
  String strVal=numberString();
  float floatValue=Float.parseFloat(strVal);
  if (floatValue == 0 || floatValue == Float.POSITIVE_INFINITY) {
    char c0=strVal.charAt(0);
    if (c0 > '0' && c0 <= '9') {
      throw new JSONException("float overflow : " + strVal);
    }
  }
  return floatValue;
}
