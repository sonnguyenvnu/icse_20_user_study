public static String trimDp(String v){
  if (v.lastIndexOf("dp") != -1) {
    return v.substring(0,v.lastIndexOf("dp"));
  }
  return v;
}
