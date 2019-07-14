public static Bitmap createScaledBitmap(Bitmap src,int dstWidth,int dstHeight,boolean filter){
  if (Build.VERSION.SDK_INT >= 21) {
    return Bitmap.createScaledBitmap(src,dstWidth,dstHeight,filter);
  }
  Matrix m;
synchronized (Bitmap.class) {
    m=sScaleMatrix;
    sScaleMatrix=null;
  }
  if (m == null) {
    m=new Matrix();
  }
  final int width=src.getWidth();
  final int height=src.getHeight();
  final float sx=dstWidth / (float)width;
  final float sy=dstHeight / (float)height;
  m.setScale(sx,sy);
  Bitmap b=createBitmap(src,0,0,width,height,m,filter);
synchronized (Bitmap.class) {
    if (sScaleMatrix == null) {
      sScaleMatrix=m;
    }
  }
  return b;
}
