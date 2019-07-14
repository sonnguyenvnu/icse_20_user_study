public static float getPixelsInCM(float cm,boolean isX){
  return (cm / 2.54f) * (isX ? displayMetrics.xdpi : displayMetrics.ydpi);
}
