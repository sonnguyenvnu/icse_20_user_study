/** 
 * ??byte???8?boolean???????
 * @param modifiers ???
 * @param i         ?? 0-7
 * @return ???bit???boolean?0false1true?
 */
public static boolean getBooleanFromByte(byte modifiers,int i){
  if (i > 7 || i < 0) {
    throw new IllegalArgumentException("Index must between 0-7!");
  }
  return ((modifiers >> i) & 0x01) == 1;
}
