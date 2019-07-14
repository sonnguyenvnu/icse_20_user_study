@NonNull public static String replaceExtension(@NonNull String path,@NonNull String extension){
  path=removeExtension(path);
  if (!TextUtils.isEmpty(extension)) {
    path+=EXTENSION_SEPARATOR + extension;
  }
  return path;
}
