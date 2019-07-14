/** 
 * @return true if the ImageView exists, and it's Drawable exists
 */
private static boolean hasDrawable(ImageView imageView){
  return null != imageView && null != imageView.getDrawable();
}
