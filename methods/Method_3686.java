/** 
 * ???????
 * @param sString
 * @return
 */
public static boolean isAllDelimiter(byte[] sString){
  int nLen=sString.length;
  int i=0;
  while (i < nLen - 1 && (getUnsigned(sString[i]) == 161 || getUnsigned(sString[i]) == 163)) {
    i+=2;
  }
  if (i < nLen)   return false;
  return true;
}
