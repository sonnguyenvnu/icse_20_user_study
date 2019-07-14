/** 
 * ?byte????????8?boolean????bit????boolean??
 * @param b byte
 * @return boolean??
 */
public static boolean[] byte2Booleans(byte b){
  boolean[] array=new boolean[8];
  for (int i=7; i >= 0; i--) {
    array[i]=(b & 1) == 1;
    b=(byte)(b >> 1);
  }
  return array;
}
