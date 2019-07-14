private String fetchCurrentSubword(){
  JEditTextArea ta=editor.getTextArea();
  int off=ta.getCaretPosition();
  if (off < 0)   return null;
  int line=ta.getCaretLine();
  if (line < 0)   return null;
  String s=ta.getLineText(line);
  int x=ta.getCaretPosition() - ta.getLineStartOffset(line) - 1, x1=x - 1;
  if (x >= s.length() || x < 0)   return null;
  if (Base.DEBUG)   System.out.print(" x char: " + s.charAt(x));
  String word=(x < s.length() ? s.charAt(x) : "") + "";
  if (s.trim().length() == 1) {
    word=word.trim();
    if (word.endsWith("."))     word=word.substring(0,word.length() - 1);
    return word;
  }
  if (word.equals("."))   return null;
  int i=0;
  while (true) {
    i++;
    if (x1 >= 0) {
      if (Character.isLetterOrDigit(s.charAt(x1)) || s.charAt(x1) == '_') {
        word=s.charAt(x1--) + word;
      }
 else {
        break;
      }
    }
 else {
      break;
    }
    if (i > 200) {
      break;
    }
  }
  if (Character.isDigit(word.charAt(0)))   return null;
  word=word.trim();
  if (word.endsWith("."))   word=word.substring(0,word.length() - 1);
  return word;
}
