public static boolean isValidPath(@NonNull String path){
  return !TextUtils.isEmpty(path) && path.indexOf('\0') == -1;
}
