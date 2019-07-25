private static String property(String string){
  int length=string.length();
  if (length == 0) {
    return "";
  }
 else   if (length == 1) {
    return string.toLowerCase();
  }
 else {
    return string.substring(0,1).toLowerCase() + string.substring(1);
  }
}
