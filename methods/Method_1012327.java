@Override public void destroy(){
synchronized (LOCK) {
    BITMAP_CACHE.evictAll();
    for (int i=0; i < NUM_STRIPS; i++) {
      final Bitmap strip=(Bitmap)STRIP_REFS[i].get();
      if (strip != null) {
        strip.recycle();
        STRIP_REFS[i].clear();
      }
    }
  }
}
