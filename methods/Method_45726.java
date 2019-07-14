/** 
 * ??byte???8?boolean???????
 * @param modifiers ???
 * @param i         ?? 0-7
 * @param bool      ?????
 * @return ?????
 */
public static byte setBooleanToByte(byte modifiers,int i,boolean bool){
  boolean old=getBooleanFromByte(modifiers,i);
  if (old && !bool) {
    return (byte)(modifiers - (1 << i));
  }
 else   if (!old && bool) {
    return (byte)(modifiers + (1 << i));
  }
  return modifiers;
}
