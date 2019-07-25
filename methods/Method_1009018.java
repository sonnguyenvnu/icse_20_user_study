private static String dump(final long value){
  StringBuffer buf=new StringBuffer();
  buf.setLength(0);
  for (int j=0; j < 8; j++) {
    buf.append(_hexcodes[((int)(value >> _shifts[j + _shifts.length - 8])) & 15]);
  }
  return buf.toString();
}
