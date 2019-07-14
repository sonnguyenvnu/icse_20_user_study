public static boolean isPhoto(@Nullable String mimeType){
  return mimeType != null && mimeType.startsWith("image/");
}
