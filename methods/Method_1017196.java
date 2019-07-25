private QueryListener parse(Function<HeroicQueryParser,ParserRuleContext> op,String input){
  final HeroicQueryLexer lexer=new HeroicQueryLexer(new ANTLRInputStream(input));
  final CommonTokenStream tokens=new CommonTokenStream(lexer);
  final HeroicQueryParser parser=new HeroicQueryParser(tokens);
  parser.removeErrorListeners();
  parser.setErrorHandler(new BailErrorStrategy());
  final ParserRuleContext context;
  try {
    context=op.apply(parser);
  }
 catch (  final ParseCancellationException e) {
    if (!(e.getCause() instanceof RecognitionException)) {
      throw e;
    }
    throw toParseException((RecognitionException)e.getCause());
  }
  final QueryListener listener=new QueryListener();
  ParseTreeWalker.DEFAULT.walk(listener,context);
  final Token last=lexer.getToken();
  if (last.getType() != Token.EOF) {
    throw new ParseException(String.format("garbage at end of string: '%s'",last.getText()),null,last.getLine(),last.getCharPositionInLine());
  }
  return listener;
}
