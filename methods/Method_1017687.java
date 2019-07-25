@Override public void restore(@NotNull LexerPosition lexerPosition){
  currentOffset=lexerPosition.getOffset();
  lexemeStart=((MarkdownLexerPosition)lexerPosition).getStart();
  lexemeEnd=((MarkdownLexerPosition)lexerPosition).getEnd();
}
