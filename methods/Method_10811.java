/** 
 * ????
 * @param src     ???
 * @param x       ????x
 * @param y       ????y
 * @param width   ????
 * @param height  ????
 * @param recycle ????
 * @return ??????
 */
public static Bitmap clip(Bitmap src,int x,int y,int width,int height,boolean recycle){
  if (isEmptyBitmap(src)) {
    return null;
  }
  Bitmap ret=Bitmap.createBitmap(src,x,y,width,height);
  if (recycle && !src.isRecycled()) {
    src.recycle();
  }
  return ret;
}
