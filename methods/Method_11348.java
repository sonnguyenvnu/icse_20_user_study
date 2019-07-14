/** 
 * Calculates sample size to fit the source image in given bounds.
 */
private int calculateInSampleSize(float scale){
  if (minimumTileDpi > 0) {
    DisplayMetrics metrics=getResources().getDisplayMetrics();
    float averageDpi=(metrics.xdpi + metrics.ydpi) / 2;
    scale=(minimumTileDpi / averageDpi) * scale;
  }
  int reqWidth=(int)(sWidth() * scale);
  int reqHeight=(int)(sHeight() * scale);
  int inSampleSize=1;
  if (reqWidth == 0 || reqHeight == 0) {
    return 32;
  }
  if (sHeight() > reqHeight || sWidth() > reqWidth) {
    final int heightRatio=Math.round((float)sHeight() / (float)reqHeight);
    final int widthRatio=Math.round((float)sWidth() / (float)reqWidth);
    inSampleSize=heightRatio < widthRatio ? heightRatio : widthRatio;
  }
  int power=1;
  while (power * 2 < inSampleSize) {
    power=power * 2;
  }
  return power;
}
