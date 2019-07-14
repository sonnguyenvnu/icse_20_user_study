@Override public final Reader asReader(){
  ensureResponseEvaluated();
  return super.asReader();
}
