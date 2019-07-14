@Override public void cdata(final CharSequence cdata){
  for (  TagVisitor target : targets) {
    target.cdata(cdata);
  }
}
