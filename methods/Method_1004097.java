public IExpression parse() throws IOException {
  tokenizer.nextToken();
  final IExpression e=term();
  expect(TT_EOF);
  return e;
}
