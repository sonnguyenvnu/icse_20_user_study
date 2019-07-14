private Qname convertQname(List<org.python.antlr.ast.Name> in) throws Exception {
  if (in == null) {
    return null;
  }
  Qname out=null;
  int end=-1;
  for (int i=in.size() - 1; i >= 0; i--) {
    org.python.antlr.ast.Name n=in.get(i);
    if (end == -1) {
      end=n.getCharStopIndex();
    }
    Name nn=(Name)n.accept(this);
    out=new Qname(out,nn,n.getCharStartIndex(),end);
  }
  return out;
}
