public String group(){
  final long now=System.currentTimeMillis();
  try {
    return matcher.group();
  }
  finally {
    addTime(System.currentTimeMillis() - now);
  }
}
