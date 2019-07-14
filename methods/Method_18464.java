public int sipsToPixels(float dips){
  final float scale=mResources.getDisplayMetrics().scaledDensity;
  return FastMath.round(dips * scale);
}
