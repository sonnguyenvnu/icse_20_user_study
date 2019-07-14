@Override protected Token handleAlternativeStringLiteral(PeekingReader reader,ParserContext context,int pos,int line,int col) throws IOException {
  reader.swallow(2);
  String closeQuote=computeAlternativeCloseQuote((char)reader.read());
  reader.swallowUntilExcluding(closeQuote);
  reader.swallow(closeQuote.length());
  return new Token(TokenType.STRING,pos,line,col,null,null,context.getParensDepth());
}
