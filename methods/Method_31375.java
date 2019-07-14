@Override protected Token handleAlternativeStringLiteral(PeekingReader reader,ParserContext context,int pos,int line,int col) throws IOException {
  reader.swallow();
  reader.swallowUntilExcludingWithEscape('"',true,'\\');
  return new Token(TokenType.STRING,pos,line,col,null,null,context.getParensDepth());
}
