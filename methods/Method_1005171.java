private final static String format(short shortval){
  String formatted=Integer.toHexString(shortval);
  StringBuilder buf=new StringBuilder("0000");
  buf.replace(4 - formatted.length(),4,formatted);
  return buf.toString();
}
