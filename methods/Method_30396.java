public static boolean openFrodoUri(Uri uri,Context context){
  return isFrodoUri(uri) && openUri(uri,context);
}
