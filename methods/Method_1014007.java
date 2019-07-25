@Override public String format(String pattern){
  try {
    return String.format(pattern,value.toBigIntegerExact());
  }
 catch (  ArithmeticException ae) {
  }
catch (  IllegalFormatConversionException ifce) {
  }
  return String.format(pattern,value);
}
