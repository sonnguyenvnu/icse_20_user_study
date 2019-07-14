protected Token handleKeyword(PeekingReader reader,ParserContext context,int pos,int line,int col,String keyword) throws IOException {
  return new Token(TokenType.KEYWORD,pos,line,col,keywordToUpperCase(keyword),keyword,context.getParensDepth());
}
