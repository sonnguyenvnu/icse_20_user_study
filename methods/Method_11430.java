/** 
 * Recreates assets (like bitmaps) when layout size has been changed
 * @param width  New spinnerwheel width
 * @param height New spinnerwheel height
 */
@Override protected void recreateAssets(int width,int height){
  mSpinBitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
  mSeparatorsBitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
  setSelectorPaintCoeff(0);
}
