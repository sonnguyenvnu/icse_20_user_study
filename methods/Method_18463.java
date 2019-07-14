public int dipsToPixels(float dips){
  final float scale=mResources.getDisplayMetrics().density;
  return FastMath.round(dips * scale);
}
