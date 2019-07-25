@Override public String format(String fieldEntry){
  if (fieldEntry == null) {
    return null;
  }
  StringBuilder sb=new StringBuilder(fieldEntry.length());
  for (  char c : fieldEntry.toCharArray()) {
    if (!Character.isWhitespace(c) || Character.isSpaceChar(c)) {
      sb.append(c);
    }
  }
  return sb.toString();
}
