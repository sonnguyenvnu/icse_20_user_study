public static int setMinValue(int color,float newValue){
  float hsv[]=new float[3];
  Color.colorToHSV(color,hsv);
  hsv[2]=Math.max(hsv[2],newValue);
  return Color.HSVToColor(hsv);
}
