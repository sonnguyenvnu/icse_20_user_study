private Node convExpr(PythonTree e) throws Exception {
  if (e == null) {
    return null;
  }
  Object o=e.accept(this);
  if (o instanceof Node) {
    return (Node)o;
  }
  return null;
}
