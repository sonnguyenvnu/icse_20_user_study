private static String dump(final byte value){
  StringBuffer buf=new StringBuffer();
  buf.setLength(0);
  for (int j=0; j < 2; j++) {
    buf.append(_hexcodes[(value >> _shifts[j + 6]) & 15]);
  }
  return buf.toString();
}
