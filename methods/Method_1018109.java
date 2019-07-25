@Override public boolean validate(Number value){
  if (value == null) {
    return true;
  }
  double dval=value.doubleValue();
  if (min != null) {
    if (dval < min) {
      return false;
    }
  }
  if (max != null) {
    if (dval > max) {
      return false;
    }
  }
  return true;
}
