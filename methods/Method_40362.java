private List<Name> convertListName(List<org.python.antlr.ast.Name> in) throws Exception {
  List<Name> out=new ArrayList<Name>(in == null ? 0 : in.size());
  if (in != null) {
    for (    expr e : in) {
      Name nn=(Name)e.accept(this);
      if (nn != null) {
        out.add(nn);
      }
    }
  }
  return out;
}
