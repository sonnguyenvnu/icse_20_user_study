private List<Node> convertListExpr(List<expr> in) throws Exception {
  List<Node> out=new ArrayList<Node>(in == null ? 0 : in.size());
  if (in != null) {
    for (    expr e : in) {
      Node nx=(Node)e.accept(this);
      if (nx != null) {
        out.add(nx);
      }
    }
  }
  return out;
}
