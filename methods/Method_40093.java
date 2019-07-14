@Nullable public Str getDocString(){
  Node body=null;
  if (this instanceof FunctionDef) {
    body=((FunctionDef)this).body;
  }
 else   if (this instanceof ClassDef) {
    body=((ClassDef)this).body;
  }
 else   if (this instanceof Module) {
    body=((Module)this).body;
  }
  if (body instanceof Block && ((Block)body).seq.size() >= 1) {
    Node firstExpr=((Block)body).seq.get(0);
    if (firstExpr instanceof Expr) {
      Node docstrNode=((Expr)firstExpr).value;
      if (docstrNode != null && docstrNode instanceof Str) {
        return (Str)docstrNode;
      }
    }
  }
  return null;
}
