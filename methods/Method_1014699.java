/** 
 * ????
 * @param bitmap
 * @return
 */
public static Bitmap emboss(Bitmap bitmap){
  int width=bitmap.getWidth();
  int height=bitmap.getHeight();
  Bitmap newBitmap=Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);
  int pixR=0;
  int pixG=0;
  int pixB=0;
  int pixColor=0;
  int newR=0;
  int newG=0;
  int newB=0;
  int[] pixels=new int[width * height];
  bitmap.getPixels(pixels,0,width,0,0,width,height);
  int pos=0;
  for (int i=1, length=height - 1; i < length; i++) {
    for (int k=1, len=width - 1; k < len; k++) {
      pos=i * width + k;
      pixColor=pixels[pos];
      pixR=Color.red(pixColor);
      pixG=Color.green(pixColor);
      pixB=Color.blue(pixColor);
      pixColor=pixels[pos + 1];
      newR=Color.red(pixColor) - pixR + 127;
      newG=Color.green(pixColor) - pixG + 127;
      newB=Color.blue(pixColor) - pixB + 127;
      newR=Math.min(255,Math.max(0,newR));
      newG=Math.min(255,Math.max(0,newG));
      newB=Math.min(255,Math.max(0,newB));
      pixels[pos]=Color.argb(255,newR,newG,newB);
    }
  }
  newBitmap.setPixels(pixels,0,width,0,0,width,height);
  return newBitmap;
}
