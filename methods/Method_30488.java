public static String getErrorString(Throwable error,Context context){
  return context.getString(getErrorStringRes(error));
}
