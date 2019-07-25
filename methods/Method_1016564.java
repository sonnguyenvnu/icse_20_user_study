List<FormatterToken> parse(final String argSql){
  fPos=0;
  fBefore=argSql;
  final List<FormatterToken> list=new ArrayList<>();
  for (; ; ) {
    final FormatterToken token=nextToken();
    if (token.getType() == TokenType.END) {
      break;
    }
    list.add(token);
  }
  return list;
}
