public final Number decimalValue(boolean decimal){
  char chLocal=charAt(np + sp - 1);
  try {
    if (chLocal == 'F') {
      return Float.parseFloat(numberString());
    }
    if (chLocal == 'D') {
      return Double.parseDouble(numberString());
    }
    if (decimal) {
      return decimalValue();
    }
 else {
      return doubleValue();
    }
  }
 catch (  NumberFormatException ex) {
    throw new JSONException(ex.getMessage() + ", " + info());
  }
}
