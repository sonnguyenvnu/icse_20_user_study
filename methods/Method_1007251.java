protected boolean match(Token t){
  if (MULTI.equals(t) || SINGLE.equals(t)) {
    return false;
  }
  if (MULTI.equals(this) || SINGLE.equals(this)) {
    return true;
  }
  return equals(t);
}
