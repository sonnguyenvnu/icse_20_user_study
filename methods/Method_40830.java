public boolean matchString(String open,String close){
  String matched=delimMap.get(open);
  if (matched != null && matched.equals(close)) {
    return true;
  }
 else {
    return false;
  }
}
