public static boolean validate(final Object value,final double min){
  if (value == null) {
    return true;
  }
  double val=Converter.get().toDoubleValue(value);
  return val > min;
}
