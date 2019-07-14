private Object parseTermValue(Object termValue){
  if (termValue instanceof SQLNumericLiteralExpr) {
    termValue=((SQLNumericLiteralExpr)termValue).getNumber();
    if (termValue instanceof BigDecimal || termValue instanceof Double) {
      termValue=((Number)termValue).doubleValue();
    }
 else     if (termValue instanceof Float) {
      termValue=((Number)termValue).floatValue();
    }
 else     if (termValue instanceof BigInteger || termValue instanceof Long) {
      termValue=((Number)termValue).longValue();
    }
 else     if (termValue instanceof Integer) {
      termValue=((Number)termValue).intValue();
    }
 else     if (termValue instanceof Short) {
      termValue=((Number)termValue).shortValue();
    }
 else     if (termValue instanceof Byte) {
      termValue=((Number)termValue).byteValue();
    }
  }
 else   if (termValue instanceof SQLBooleanExpr) {
    termValue=((SQLBooleanExpr)termValue).getValue();
  }
 else {
    termValue=termValue.toString();
  }
  return termValue;
}
