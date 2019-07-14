@Override public boolean handles(String s){
  return s.substring(s.length() - 4,s.length()).toLowerCase().equals(".apk");
}
