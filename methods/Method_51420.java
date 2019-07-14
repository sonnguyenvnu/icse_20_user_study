public String valueErrorFor(T value){
  if (value == null) {
    return "Missing value";
  }
  double number=value.doubleValue();
  if (number > upperLimit.doubleValue() || number < lowerLimit.doubleValue()) {
    return value + " is out of range " + rangeString(lowerLimit,upperLimit);
  }
  return null;
}
