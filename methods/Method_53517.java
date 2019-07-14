/** 
 * byte?? ?? 16??char?????????????
 * @param byteArr  byte??
 * @param toDigits ???????char[]
 * @return 16??char??
 */
private static char[] byteArr2CharArrHex(@NotNull byte[] byteArr,@NotNull char[] toDigits){
  int index=0;
  char[] hexChar=new char[byteArr.length * 2];
  for (int i=0; i < byteArr.length; i++) {
    hexChar[index++]=toDigits[byteArr[i] >> 4 & 0xF];
    hexChar[index++]=toDigits[byteArr[i] & 0xF];
    hexChar[index++]=toDigits[(0xF0 & byteArr[i]) >>> 4];
    hexChar[index++]=toDigits[0x0F & byteArr[i]];
  }
  return hexChar;
}
