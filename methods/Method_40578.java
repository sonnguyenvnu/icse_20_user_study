public void setCWD(String cd){
  if (cd != null) {
    cwd=_.unifyPath(cd);
  }
}
