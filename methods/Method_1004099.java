private IExpression factor() throws IOException {
  final IExpression e;
  if (accept('(')) {
    e=term();
    expect(')');
  }
 else {
    expect(TT_NUMBER);
    e=new Const(tokenizer.nval);
  }
  return e;
}
