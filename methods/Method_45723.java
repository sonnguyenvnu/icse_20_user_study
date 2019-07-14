/** 
 * ??????8?boolean????bit????boolean?????byte
 * @param array boolean??
 * @return byte
 */
public static byte booleansToByte(boolean[] array){
  if (array != null && array.length > 0) {
    byte b=0;
    for (int i=0; i <= 7; i++) {
      if (array[i]) {
        int nn=(1 << (7 - i));
        b+=nn;
      }
    }
    return b;
  }
  return 0;
}
