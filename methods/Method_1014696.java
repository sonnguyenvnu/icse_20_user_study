/** 
 * ????
 * @param bitmap
 * @param centerX ???X????
 * @param centerY ???Y????
 * @return
 */
public static Bitmap sunshine(Bitmap bitmap,int centerX,int centerY){
  final int width=bitmap.getWidth();
  final int height=bitmap.getHeight();
  Bitmap newBitmap=Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);
  int pixR=0;
  int pixG=0;
  int pixB=0;
  int pixColor=0;
  int newR=0;
  int newG=0;
  int newB=0;
  int radius=Math.min(centerX,centerY);
  final float strength=150F;
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
      newR=pixR;
      newG=pixG;
      newB=pixB;
      int distance=(int)(Math.pow((centerY - i),2) + Math.pow(centerX - k,2));
      if (distance < radius * radius) {
        int result=(int)(strength * (1.0 - Math.sqrt(distance) / radius));
        newR=pixR + result;
        newG=pixG + result;
        newB=pixB + result;
      }
      newR=Math.min(255,Math.max(0,newR));
      newG=Math.min(255,Math.max(0,newG));
      newB=Math.min(255,Math.max(0,newB));
      pixels[pos]=Color.argb(255,newR,newG,newB);
    }
  }
  newBitmap.setPixels(pixels,0,width,0,0,width,height);
  return newBitmap;
}
