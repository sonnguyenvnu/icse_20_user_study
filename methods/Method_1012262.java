/** 
 * ????
 */
public void recycle(){
  if (mIconBitmap != null) {
    mIconBitmap.recycle();
    mIconBitmap=null;
  }
  mIconPaint=null;
}
