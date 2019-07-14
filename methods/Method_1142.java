/** 
 * Returns the opacity of the final color which would be used for drawing. This has been inspired by Android ColorDrawable.
 * @return the opacity
 */
@Override public int getOpacity(){
  return DrawableUtils.getOpacityFromColor(DrawableUtils.multiplyColorAlpha(mColor,mAlpha));
}
