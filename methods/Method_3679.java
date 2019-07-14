/** 
 * ????????
 * @param sString
 * @return
 */
public static boolean isAllNonChinese(byte[] sString){
  int nLen=sString.length;
  int i=0;
  while (i < nLen) {
    if (getUnsigned(sString[i]) < 248 && getUnsigned(sString[i]) > 175)     return false;
    if (sString[i] < 0)     i+=2;
 else     i+=1;
  }
  return true;
}
