/** 
 * ??????
 * @param sString
 * @return
 */
public static boolean isAllIndex(byte[] sString){
  int nLen=sString.length;
  int i=0;
  while (i < nLen - 1 && getUnsigned(sString[i]) == 162) {
    i+=2;
  }
  if (i >= nLen)   return true;
  while (i < nLen && (sString[i] > 'A' - 1 && sString[i] < 'Z' + 1) || (sString[i] > 'a' - 1 && sString[i] < 'z' + 1)) {
    i+=1;
  }
  if (i < nLen)   return false;
  return true;
}
