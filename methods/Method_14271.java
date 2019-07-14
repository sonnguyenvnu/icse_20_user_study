static protected Evaluable parseGREL(String s) throws ParsingException {
  Parser parser=new Parser(s);
  return parser.getExpression();
}
