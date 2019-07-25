/** 
 * Returns val represented by the specified number of hex digits. 
 */
protected static String digits(long val,int digits){
  long hi=1L << (digits * 4);
  return Long.toHexString(hi | (val & (hi - 1))).substring(1);
}
