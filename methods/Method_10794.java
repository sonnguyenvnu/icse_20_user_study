public static int colorAtLightness(int color,float lightness){
  float[] hsv=new float[3];
  Color.colorToHSV(color,hsv);
  hsv[2]=lightness;
  return Color.HSVToColor(hsv);
}
