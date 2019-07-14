/** 
 * The width at an index position in a java char array. 
 */
public static int width(char[] chars,int index){
  char c=chars[index];
  return Character.isHighSurrogate(c) ? width(Character.toCodePoint(c,chars[index + 1])) : width(c);
}
