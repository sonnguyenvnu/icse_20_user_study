private Block convertListStmt(List<stmt> in) throws Exception {
  List<Node> out=new ArrayList<Node>(in == null ? 0 : in.size());
  if (in != null) {
    for (    stmt e : in) {
      Node nx=(Node)e.accept(this);
      if (nx != null) {
        out.add(nx);
      }
    }
  }
  return new Block(out,0,0);
}
