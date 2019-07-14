@Override protected Token handleCommentDirective(PeekingReader reader,ParserContext context,int pos,int line,int col) throws IOException {
  reader.swallow(2);
  String text=reader.readUntilExcluding("*/");
  reader.swallow(2);
  return new Token(TokenType.MULTI_LINE_COMMENT_DIRECTIVE,pos,line,col,text,text,context.getParensDepth());
}
