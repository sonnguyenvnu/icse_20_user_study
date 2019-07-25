public static float convert(String value){
  if (value == null) {
    return DEFAULT_VALUE;
  }
  float tryFloat=SCU.parseFloat(value,-1.0f);
  if (tryFloat != -1.0f) {
    return tryFloat;
  }
  if (value.endsWith(UNIT1)) {
    return SCU.parseFloat(value.substring(0,value.indexOf(UNIT1)),DEFAULT_VALUE);
  }
  if (value.endsWith(UNIT2)) {
    return SCU.parseFloat(value.substring(0,value.indexOf(UNIT2)),DEFAULT_VALUE);
  }
  if (value.endsWith(UNIT3)) {
    return SCU.parseFloat(value.substring(0,value.indexOf(UNIT3)),DEFAULT_VALUE);
  }
  return DEFAULT_VALUE;
}
