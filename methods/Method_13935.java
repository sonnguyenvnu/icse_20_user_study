public boolean isNonNull(){
  return username != null && password != null && !"null".equals(username) && !"null".equals(password);
}
