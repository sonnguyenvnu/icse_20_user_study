public static long getLong(String str){
  if (null == str || "".equals(str) || "null".equals(str)) {
    return -1;
  }
 else {
    if (str.endsWith("+")) {
      str=str.substring(0,str.length() - 1);
      return Long.valueOf(str) + 1;
    }
    return Long.valueOf(str);
  }
}
