private void handleInvalidStringRes(){
  if (hasDefault) {
    if (defaultStringRes > 0) {
      setValue(defaultStringRes);
    }
 else {
      setValue(defaultString);
    }
  }
 else {
    throw new IllegalArgumentException("0 is an invalid value for required strings.");
  }
}
