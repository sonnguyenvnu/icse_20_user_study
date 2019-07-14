@Override protected Token handleKeyword(PeekingReader reader,ParserContext context,int pos,int line,int col,String keyword) throws IOException {
  if (keywordIs("DELIMITER",keyword)) {
    String text=reader.readUntilExcluding('\n','\r').trim();
    return new Token(TokenType.NEW_DELIMITER,pos,line,col,text,text,context.getParensDepth());
  }
  return super.handleKeyword(reader,context,pos,line,col,keyword);
}
