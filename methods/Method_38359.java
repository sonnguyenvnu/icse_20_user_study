protected static boolean isReferenceChar(final String template,final int index){
  char c=template.charAt(index);
  if ((c == '+') && (template.charAt(index - 1) == '.')) {
    return true;
  }
  return isDigit(c) || isAlpha(c) || (c == '_') || (c == '.');
}
