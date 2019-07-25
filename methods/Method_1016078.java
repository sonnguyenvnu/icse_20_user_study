/** 
 * Clamp a value to the range 0..255
 */
public static int clamp(int c){
  if (c < 0) {
    return 0;
  }
  if (c > 255) {
    return 255;
  }
  return c;
}
