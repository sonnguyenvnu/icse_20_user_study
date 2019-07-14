private int adjustColorBrightness(int argb,float factor){
  float[] hsv=new float[3];
  Color.colorToHSV(argb,hsv);
  hsv[2]=Math.min(hsv[2] * factor,1f);
  return Color.HSVToColor(Color.alpha(argb),hsv);
}
