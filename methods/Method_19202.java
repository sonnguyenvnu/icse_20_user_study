@Override protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics){
  return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
}
