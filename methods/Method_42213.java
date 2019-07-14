public static Bitmap addWhiteBorder(Bitmap bmp,int borderSize){
  Bitmap bmpWithBorder=Bitmap.createBitmap(bmp.getWidth() + borderSize * 2,bmp.getHeight() + borderSize * 2,bmp.getConfig());
  Canvas canvas=new Canvas(bmpWithBorder);
  canvas.drawColor(Color.WHITE);
  canvas.drawBitmap(bmp,borderSize,borderSize,null);
  return bmpWithBorder;
}
