@Override public boolean serialize(final JsonContext jsonContext,final Double value){
  if (value.isNaN()) {
    jsonContext.writeString("NaN");
    return true;
  }
  if (value == Double.POSITIVE_INFINITY) {
    jsonContext.writeString("+Infinity");
    return true;
  }
  if (value == Double.NEGATIVE_INFINITY) {
    jsonContext.writeString("-Infinity");
    return true;
  }
  jsonContext.write(value.toString());
  return true;
}
