@Override public String format(String s){
  if (s == null) {
    return "";
  }
  String[] pageParts=s.split("[ \\-]+");
  if (pageParts.length == 2) {
    return pageParts[0];
  }
 else {
    return s;
  }
}
