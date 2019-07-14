/** 
 * byte????short
 * @param byteNumber byte??
 * @return short??
 */
public static short byte2Short(@NotNull byte[] byteNumber){
  short result=0;
  for (int i=0; i < 2; i++) {
    result<<=8;
    result|=(byteNumber[i] & 0xff);
  }
  return result;
}
