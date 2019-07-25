@Override public void handle(Context ctx) throws Exception {
  if (test.apply(ctx)) {
    ctx.insert(ifHandler);
  }
 else {
    if (elseHandler == null) {
      ctx.next();
    }
 else {
      ctx.insert(elseHandler);
    }
  }
}
