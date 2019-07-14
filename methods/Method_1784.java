/** 
 * Common code for checking that x and y are >= 0
 * @param x x coordinate to ensure is >= 0
 * @param y y coordinate to ensure is >= 0
 */
private static void checkXYSign(int x,int y){
  Preconditions.checkArgument(x >= 0,"x must be >= 0");
  Preconditions.checkArgument(y >= 0,"y must be >= 0");
}
