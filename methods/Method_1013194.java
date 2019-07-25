/** 
 * The Uncomment Method                                   * Several parsing bugs arose because the peek-ahead kludgery was confused   by comments.  The simple solution was to remove comments before           parsing.  The Uncomment method does this.  It is called on a copy of      the input used only to create the abstract syntax tree, so it doesn't     matter what it does to anything outside the algorithm.  Therefore, the    method doesn't bother to try to find the actual end of the algorithm.     It stops the processing if it finds an unmatched *), since it then        should be outside the algorithm.                                          * Bug fixed by LL on 28 Feb 2006                                           
 */
public static void uncomment(Vector inp,int begLine,int begCol) throws ParseAlgorithmException {
  int line=begLine;
  int col=begCol;
  boolean notDone=true;
  int commentDepth=0;
  StringBuffer newLine=new StringBuffer(((String)inp.elementAt(line)).substring(0,col));
  while (notDone && line < inp.size()) {
    String oldLine=(String)inp.elementAt(line);
    boolean inString=false;
    boolean afterBackslash=false;
    boolean afterAsterisk=false;
    while (notDone && col < oldLine.length()) {
      char inChar=oldLine.charAt(col);
      char outChar=inChar;
      if ((inChar == '(') && !inString && (col < oldLine.length() - 1) && (oldLine.charAt(col + 1) == '*')) {
        commentDepth=commentDepth + 1;
        outChar=' ';
        col=col + 1;
        newLine.append(outChar);
      }
 else       if ((inChar == '*') && !inString && (col < oldLine.length() - 1) && (oldLine.charAt(col + 1) == ')')) {
        if (commentDepth == 0) {
          newLine.append(inChar);
          outChar=')';
          col=col + 1;
          notDone=false;
        }
 else {
          commentDepth=commentDepth - 1;
          outChar=' ';
          col=col + 1;
          newLine.append(outChar);
        }
        ;
      }
 else       if (commentDepth == 0) {
        if (inString) {
          if (inChar == '"') {
            inString=false;
          }
 else           if ((inChar == '\\') && (col < oldLine.length() - 1)) {
            newLine.append(inChar);
            outChar=oldLine.charAt(col + 1);
            col=col + 1;
          }
          ;
        }
 else {
          if ((inChar == '\\') && (col < oldLine.length() - 1) && (oldLine.charAt(col + 1) == '*')) {
            outChar=' ';
            col=oldLine.length();
          }
 else           if (inChar == '"') {
            inString=true;
          }
        }
        ;
      }
 else {
        outChar=' ';
      }
      ;
      newLine.append(outChar);
      col=col + 1;
    }
    ;
    if (inString) {
      throw new ParseAlgorithmException("Unterminated string in line " + (line + 1));
    }
    inp.set(line,newLine.toString());
    newLine=new StringBuffer();
    col=0;
    line=line + 1;
  }
  return;
}
