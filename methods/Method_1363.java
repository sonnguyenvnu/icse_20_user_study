/** 
 * Quickly converts a byte array to a hexadecimal string representation.
 * @param array byte array, possibly zero-terminated.
 */
public static String encodeHex(byte[] array,boolean zeroTerminated){
  char[] cArray=new char[array.length * 2];
  int j=0;
  for (int i=0; i < array.length; i++) {
    int index=array[i] & 0xFF;
    if (index == 0 && zeroTerminated) {
      break;
    }
    cArray[j++]=FIRST_CHAR[index];
    cArray[j++]=SECOND_CHAR[index];
  }
  return new String(cArray,0,j);
}
