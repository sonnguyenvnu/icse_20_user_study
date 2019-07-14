/** 
 * Show an exception in the editor status bar.
 */
public void statusError(Exception e){
  e.printStackTrace();
  if (e instanceof SketchException) {
    SketchException re=(SketchException)e;
    if (!re.isStackTraceEnabled()) {
      System.err.println(re.getMessage());
    }
    if (re.hasCodeIndex()) {
      sketch.setCurrentCode(re.getCodeIndex());
    }
    if (re.hasCodeLine()) {
      int line=re.getCodeLine();
      if (line >= textarea.getLineCount()) {
        line=textarea.getLineCount() - 1;
        if (textarea.getLineText(line).length() == 0) {
          line--;
        }
      }
      if (line < 0 || line >= textarea.getLineCount()) {
        System.err.println("Bad error line: " + line);
      }
 else {
        textarea.select(textarea.getLineStartOffset(line),textarea.getLineStopOffset(line) - 1);
      }
    }
  }
  String mess=e.getMessage();
  if (mess != null) {
    String javaLang="java.lang.";
    if (mess.indexOf(javaLang) == 0) {
      mess=mess.substring(javaLang.length());
    }
    String rxString="RuntimeException: ";
    if (mess.startsWith(rxString)) {
      mess=mess.substring(rxString.length());
    }
    String illString="IllegalArgumentException: ";
    if (mess.startsWith(illString)) {
      mess=mess.substring(illString.length());
    }
    String illState="IllegalStateException: ";
    if (mess.startsWith(illState)) {
      mess=mess.substring(illState.length());
    }
    statusError(mess);
  }
}
