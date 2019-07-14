public static String getUri(Context context){
  return "content://" + getAuthority(context);
}
