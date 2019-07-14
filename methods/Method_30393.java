public static boolean openUri(Uri uri,Context context){
  if (!isUriHandled(uri)) {
    return false;
  }
  Intent intent=makeFacadeIntent(uri);
  return AppUtils.isIntentHandled(intent,context) && startActivity(intent,context);
}
