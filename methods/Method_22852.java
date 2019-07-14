/** 
 * A wrapper for the lower-level <code>markTokensImpl</code> method that is called to split a line up into tokens.
 * @param line      The line
 * @param lineIndex The line number
 */
public Token markTokens(Segment line,int lineIndex){
  if (lineIndex >= length) {
    throw new IllegalArgumentException("Tokenizing invalid line: " + lineIndex);
  }
  marker.setTokenListener(this::addToken);
  lastToken=null;
  byte prev=(lineIndex == 0) ? Token.NULL : lineInfo[lineIndex - 1];
  byte oldToken=lineInfo[lineIndex];
  byte token=marker.markTokensImpl(prev,line,lineIndex);
  marker.setTokenListener(null);
  lineInfo[lineIndex]=token;
  if (!(lastLine == lineIndex && nextLineRequested)) {
    nextLineRequested=(oldToken != token);
  }
  lastLine=lineIndex;
  addToken(0,Token.END);
  return firstToken;
}
