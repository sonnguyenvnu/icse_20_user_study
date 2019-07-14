public static int getPatternSideColor(int color){
  float[] hsb=RGBtoHSB(Color.red(color),Color.green(color),Color.blue(color));
  hsb[1]=Math.min(1.0f,hsb[1] + 0.05f);
  if (hsb[2] > 0.5f) {
    hsb[2]=Math.max(0.0f,hsb[2] * 0.90f);
  }
 else {
    hsb[2]=Math.max(0.0f,hsb[2] * 0.90f);
  }
  return HSBtoRGB(hsb[0],hsb[1],hsb[2]) | 0xff000000;
}
