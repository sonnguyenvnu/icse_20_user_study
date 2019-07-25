/** 
 * ????
 * @param bitmap
 * @return
 */
public static Bitmap film(Bitmap bitmap){
  final int MAX_VALUE=255;
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
      newR=MAX_VALUE - pixR;
      newG=MAX_VALUE - pixG;
      newB=MAX_VALUE - pixB;
      newR=Math.min(MAX_VALUE,Math.max(0,newR));
      newG=Math.min(MAX_VALUE,Math.max(0,newG));
      newB=Math.min(MAX_VALUE,Math.max(0,newB));
      pixels[pos]=Color.argb(MAX_VALUE,newR,newG,newB);
    }
  }
  newBitmap.setPixels(pixels,0,width,0,0,width,height);
  return newBitmap;
}
