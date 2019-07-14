@SuppressWarnings("Duplicates") @Override protected Token handleAlternativeStringLiteral(PeekingReader reader,ParserContext context,int pos,int line,int col) throws IOException {
  reader.swallow(2);
  reader.swallowUntilExcluding("$$");
  reader.swallow(2);
  return new Token(TokenType.STRING,pos,line,col,null,null,context.getParensDepth());
}
