@Override public void start(@NotNull CharSequence buffer,int startOffset,int endOffset,int initialState){
  this.buffer=buffer;
  this.currentOffset=this.startOffset=startOffset;
  this.endOffset=endOffset;
  lexemeIndex=initialState;
  lexerTokens=null;
  if (buffer.length() > 0) {
    lexerTokens=MultiMarkdownLexParserManager.parseMarkdown(buffer,pegdownExtensions,parsingTimeout);
  }
  lexerToken=null;
  if (lexerTokens != null && lexerTokens.length > 0) {
    lexerToken=lexerTokens[lexemeIndex];
    if (currentOffset <= lexerToken.getRange().getStart()) {
      lexerToken=MultiMarkdownLexParser.getSkippedSpaceToken(currentOffset,lexerToken.getRange().getStart());
    }
 else {
      lexemeIndex++;
    }
  }
  if (lexerToken == null) {
    lexerToken=MultiMarkdownLexParser.getSkippedSpaceToken(currentOffset,this.endOffset);
  }
  currentOffset=lexerToken.getRange().getEnd();
  if (currentOffset > endOffset) {
    currentOffset=endOffset;
  }
}
