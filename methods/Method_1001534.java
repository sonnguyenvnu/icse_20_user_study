public boolean matches(){
  final long now=System.currentTimeMillis();
  try {
    return matcher.matches();
  }
  finally {
    addTime(System.currentTimeMillis() - now);
  }
}
