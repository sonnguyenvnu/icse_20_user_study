public static float lightnessOfColor(int color){
  float[] hsv=new float[3];
  Color.colorToHSV(color,hsv);
  return hsv[2];
}
