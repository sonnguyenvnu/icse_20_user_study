public static int dp2px(double dpValue){
  float scaleRatio=TangramViewMetrics.screenDensity();
  final float scale=scaleRatio < 0 ? 1.0f : scaleRatio;
  int finalValue;
  if (dpValue >= 0) {
    finalValue=(int)(dpValue * scale + 0.5f);
  }
 else {
    finalValue=-((int)(-dpValue * scale + 0.5f));
  }
  return finalValue;
}
