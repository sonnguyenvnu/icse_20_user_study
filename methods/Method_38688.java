/** 
 * Parses un-quoted string content.
 */
protected String parseUnquotedStringContent(){
  final int startNdx=ndx;
  while (true) {
    final char c=input[ndx];
    if (c <= ' ' || CharUtil.equalsOne(c,UNQUOTED_DELIMETERS)) {
      final int currentNdx=ndx;
      skipWhiteSpaces();
      return new String(input,startNdx,currentNdx - startNdx);
    }
    ndx++;
  }
}
