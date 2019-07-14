@MainThread @RequiresApi(Build.VERSION_CODES.LOLLIPOP) private static void flushLollipop(@NonNull Resources resources){
  if (!sDrawableCacheFieldInitialized) {
    try {
      sDrawableCacheField=Resources.class.getDeclaredField("mDrawableCache");
      sDrawableCacheField.setAccessible(true);
    }
 catch (    NoSuchFieldException e) {
      e.printStackTrace();
    }
    sDrawableCacheFieldInitialized=true;
  }
  if (sDrawableCacheField == null) {
    return;
  }
  Map drawableCache=null;
  try {
    drawableCache=(Map)sDrawableCacheField.get(resources);
  }
 catch (  IllegalAccessException e) {
    e.printStackTrace();
  }
  if (drawableCache == null) {
    return;
  }
  drawableCache.clear();
}
