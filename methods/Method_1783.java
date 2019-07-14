/** 
 * Common code for checking that width and height are > 0
 * @param width width to ensure is > 0
 * @param height height to ensure is > 0
 */
private static void checkWidthHeight(int width,int height){
  Preconditions.checkArgument(width > 0,"width must be > 0");
  Preconditions.checkArgument(height > 0,"height must be > 0");
}
