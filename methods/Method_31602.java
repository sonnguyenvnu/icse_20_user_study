private boolean isKeywordPart(int r){
  return r != -1 && ((char)r == '_' || (char)r == '$' || Character.isLetterOrDigit((char)r));
}
