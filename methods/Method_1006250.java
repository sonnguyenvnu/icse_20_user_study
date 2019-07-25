private boolean matches(String filename,String citeKey){
  boolean startsWithKey=filename.startsWith(citeKey);
  if (startsWithKey) {
    char charAfterKey=filename.charAt(citeKey.length());
    return !BibtexKeyGenerator.APPENDIX_CHARACTERS.contains(Character.toString(charAfterKey));
  }
  return false;
}
