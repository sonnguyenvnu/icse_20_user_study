private IExpression term() throws IOException {
  IExpression e=product();
  while (true) {
    if (accept('+')) {
      e=new Add(e,product());
    }
 else     if (accept('-')) {
      e=new Sub(e,product());
    }
 else {
      return e;
    }
  }
}
