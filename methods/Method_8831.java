public void setLocation(float value){
  int color=colorForLocation(location=value);
  swatchPaint.setColor(color);
  float hsv[]=new float[3];
  Color.colorToHSV(color,hsv);
  if (hsv[0] < 0.001 && hsv[1] < 0.001 && hsv[2] > 0.92f) {
    int c=(int)((1.0f - (hsv[2] - 0.92f) / 0.08f * 0.22f) * 255);
    swatchStrokePaint.setColor(Color.rgb(c,c,c));
  }
 else {
    swatchStrokePaint.setColor(color);
  }
  invalidate();
}
