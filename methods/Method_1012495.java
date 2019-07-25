boolean skip(String s){
  if (token.compareTo(s) != 0)   return false;
  getToken();
  return true;
}
