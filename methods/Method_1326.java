public static boolean isVideo(@Nullable String mimeType){
  return mimeType != null && mimeType.startsWith("video/");
}
