private static void throwGlError(String errorMsg){
  Log.e(TAG,errorMsg);
  if (ExoPlayerLibraryInfo.GL_ASSERTIONS_ENABLED) {
    throw new RuntimeException(errorMsg);
  }
}
