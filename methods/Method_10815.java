/** 
 * ????
 * @param src              ????
 * @param reflectionHeight ????
 * @param recycle          ????
 * @return ?????
 */
public static Bitmap addReflection(Bitmap src,int reflectionHeight,boolean recycle){
  if (isEmptyBitmap(src)) {
    return null;
  }
  final int REFLECTION_GAP=0;
  int srcWidth=src.getWidth();
  int srcHeight=src.getHeight();
  if (0 == srcWidth || srcHeight == 0) {
    return null;
  }
  Matrix matrix=new Matrix();
  matrix.preScale(1,-1);
  Bitmap reflectionBitmap=Bitmap.createBitmap(src,0,srcHeight - reflectionHeight,srcWidth,reflectionHeight,matrix,false);
  if (null == reflectionBitmap) {
    return null;
  }
  Bitmap ret=Bitmap.createBitmap(srcWidth,srcHeight + reflectionHeight,src.getConfig());
  Canvas canvas=new Canvas(ret);
  canvas.drawBitmap(src,0,0,null);
  canvas.drawBitmap(reflectionBitmap,0,srcHeight + REFLECTION_GAP,null);
  Paint paint=new Paint();
  paint.setAntiAlias(true);
  LinearGradient shader=new LinearGradient(0,srcHeight,0,ret.getHeight() + REFLECTION_GAP,0x70FFFFFF,0x00FFFFFF,Shader.TileMode.MIRROR);
  paint.setShader(shader);
  paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
  canvas.save();
  canvas.drawRect(0,srcHeight,srcWidth,ret.getHeight() + REFLECTION_GAP,paint);
  canvas.restore();
  if (!reflectionBitmap.isRecycled()) {
    reflectionBitmap.recycle();
  }
  if (recycle && !src.isRecycled()) {
    src.recycle();
  }
  return ret;
}
