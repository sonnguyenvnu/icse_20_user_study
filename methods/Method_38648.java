@Override public boolean serialize(final JsonContext jsonContext,final Float value){
  if (value.isNaN()) {
    jsonContext.writeString("NaN");
    return true;
  }
  if (value == Float.POSITIVE_INFINITY) {
    jsonContext.writeString("+Infinity");
    return true;
  }
  if (value == Float.NEGATIVE_INFINITY) {
    jsonContext.writeString("-Infinity");
    return true;
  }
  jsonContext.write(value.toString());
  return true;
}
