/** 
 * ??????????????
 * @param charArray ??
 */
public static void normalization(char[] charArray){
  assert charArray != null;
  for (int i=0; i < charArray.length; i++) {
    charArray[i]=CONVERT[charArray[i]];
  }
}
