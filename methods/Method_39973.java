public static boolean validate(final Object value,final double min,final double max){
  if (value == null) {
    return true;
  }
  double val=Converter.get().toDoubleValue(value);
  return val >= min && val <= max;
}
