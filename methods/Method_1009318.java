@Override public void value(double value){
  String str=Double.toString(value);
  if (str.contains("E")) {
    value(new BigDecimal(value));
  }
 else {
    sb.append(str);
  }
}
