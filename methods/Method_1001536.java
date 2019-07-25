public boolean find(){
  final long now=System.currentTimeMillis();
  try {
    return matcher.find();
  }
  finally {
    addTime(System.currentTimeMillis() - now);
  }
}
