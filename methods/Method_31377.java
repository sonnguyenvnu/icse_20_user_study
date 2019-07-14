@Override protected boolean isCommentDirective(String text){
  return text.length() >= 8 && text.charAt(0) == '/' && text.charAt(1) == '*' && text.charAt(2) == '!' && isDigit(text.charAt(3)) && isDigit(text.charAt(4)) && isDigit(text.charAt(5)) && isDigit(text.charAt(6)) && isDigit(text.charAt(7));
}
