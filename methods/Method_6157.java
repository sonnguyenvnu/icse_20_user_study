public static int getPatternColor(int color){
  float[] hsb=RGBtoHSB(Color.red(color),Color.green(color),Color.blue(color));
  if (hsb[1] > 0.0f || (hsb[2] < 1.0f && hsb[2] > 0.0f)) {
    hsb[1]=Math.min(1.0f,hsb[1] + 0.05f + 0.1f * (1.0f - hsb[1]));
  }
  if (hsb[2] > 0.5f) {
    hsb[2]=Math.max(0.0f,hsb[2] * 0.65f);
  }
 else {
    hsb[2]=Math.max(0.0f,Math.min(1.0f,1.0f - hsb[2] * 0.65f));
  }
  return HSBtoRGB(hsb[0],hsb[1],hsb[2]) & 0x66ffffff;
}
