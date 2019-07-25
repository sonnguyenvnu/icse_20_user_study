/** 
 * Process the content of a file for converting mangled J2CL names to minified (but still pretty and unique) versions and strips block comments.
 */
public String minify(String filePath,String content){
  String fileKey=extractFileKey(filePath);
  if (unusedFiles.contains(fileKey)) {
    return "";
  }
  String minifiedContent=minifiedContentByContent.get(content);
  if (minifiedContent != null) {
    return minifiedContent;
  }
  boolean[] unusedLines=unusedLinesPerFile.get(fileKey);
  char[] chars=getChars(content);
  StringBuilder minifiedContentBuffer=new StringBuilder();
  StringBuilder identifierBuffer=new StringBuilder();
  int lastParseState=S_NON_IDENTIFIER;
  int lineNumber=0;
  boolean skippingLine=unusedLines != null && unusedLines[lineNumber];
  for (int i=0; i < chars.length; i++) {
    char c=chars[i];
    if (unusedLines != null) {
      if (c == '\n') {
        lineNumber++;
        skippingLine=unusedLines.length > lineNumber && unusedLines[lineNumber];
      }
 else       if (skippingLine) {
        continue;
      }
    }
    int parseState=nextState[lastParseState][c < 256 ? c : 0];
    TransitionFunction transitionFunction=transFn[lastParseState][parseState];
    identifierBuffer=transitionFunction.transition(minifiedContentBuffer,identifierBuffer,c);
    lastParseState=parseState;
  }
  checkState(unusedLines == null || lineNumber >= unusedLines.length - 1);
  TransitionFunction transitionFunction=transFn[lastParseState][S_END_STATE];
  transitionFunction.transition(minifiedContentBuffer,identifierBuffer,(char)0);
  minifiedContent=minifiedContentBuffer.toString();
  minifiedContentByContent.put(content,minifiedContent);
  return minifiedContent;
}
