/** 
 * Reads a null, boolean, numeric or unquoted string literal value. Numeric values will be returned as an Integer, Long, or Double, in that order of preference.
 */
private Object readLiteral() throws JSONException {
  String literal=nextToInternal("{}[]/\\:,=;# \t\f");
  if (literal.length() == 0) {
    throw syntaxError("Expected literal value");
  }
 else   if ("null".equalsIgnoreCase(literal)) {
    return JSONObject.NULL;
  }
 else   if ("true".equalsIgnoreCase(literal)) {
    return Boolean.TRUE;
  }
 else   if ("false".equalsIgnoreCase(literal)) {
    return Boolean.FALSE;
  }
  if (literal.indexOf('.') == -1) {
    int base=10;
    String number=literal;
    if (number.startsWith("0x") || number.startsWith("0X")) {
      number=number.substring(2);
      base=16;
    }
 else     if (number.startsWith("0") && number.length() > 1) {
      number=number.substring(1);
      base=8;
    }
    try {
      long longValue=Long.parseLong(number,base);
      if (longValue <= Integer.MAX_VALUE && longValue >= Integer.MIN_VALUE) {
        return (int)longValue;
      }
 else {
        return longValue;
      }
    }
 catch (    NumberFormatException e) {
    }
  }
  try {
    return Double.valueOf(literal);
  }
 catch (  NumberFormatException ignored) {
  }
  return new String(literal);
}
