@DoNotStrip private static float getScaleFromOptions(BitmapFactory.Options options){
  float scale=1.0f;
  if (options != null) {
    int sampleSize=options.inSampleSize;
    if (sampleSize > 1) {
      scale=1.0f / (float)sampleSize;
    }
    if (options.inScaled) {
      int density=options.inDensity;
      int targetDensity=options.inTargetDensity;
      int screenDensity=options.inScreenDensity;
      if (density != 0 && targetDensity != 0 && density != screenDensity) {
        scale=targetDensity / (float)density;
      }
    }
  }
  return scale;
}
