/** 
 * Extracts image pixels into byte array "pixels"
 */
private void getImagePixels(){
  int w=image.getWidth();
  int h=image.getHeight();
  if ((w != width) || (h != height)) {
    Bitmap temp=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
    Canvas canvas=new Canvas(temp);
    canvas.drawBitmap(temp,0,0,null);
    image=temp;
  }
  int[] pixelsInt=new int[w * h];
  image.getPixels(pixelsInt,0,w,0,0,w,h);
  pixels=new byte[pixelsInt.length * 3];
  int pixelsIndex=0;
  hasTransparentPixels=false;
  int totalTransparentPixels=0;
  for (  final int pixel : pixelsInt) {
    if (pixel == Color.TRANSPARENT) {
      totalTransparentPixels++;
    }
    pixels[pixelsIndex++]=(byte)(pixel & 0xFF);
    pixels[pixelsIndex++]=(byte)((pixel >> 8) & 0xFF);
    pixels[pixelsIndex++]=(byte)((pixel >> 16) & 0xFF);
  }
  double transparentPercentage=100 * totalTransparentPixels / (double)pixelsInt.length;
  hasTransparentPixels=transparentPercentage > MIN_TRANSPARENT_PERCENTAGE;
  Timber.d("got pixels for frame with " + transparentPercentage + "% transparent pixels");
}
