private static float pixels(Resources resources,int dips){
  final float scale=resources.getDisplayMetrics().density;
  return dips * scale + 0.5f;
}
