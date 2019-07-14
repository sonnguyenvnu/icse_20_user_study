@SuppressWarnings("Duplicates") @Override protected Token handleAlternativeStringLiteral(PeekingReader reader,ParserContext context,int pos,int line,int col) throws IOException {
  String dollarQuote=(char)reader.read() + reader.readUntilIncluding('$');
  reader.swallowUntilExcluding(dollarQuote);
  reader.swallow(dollarQuote.length());
  return new Token(TokenType.STRING,pos,line,col,null,null,context.getParensDepth());
}
