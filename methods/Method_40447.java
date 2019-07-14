@Override public boolean visit(Name n){
  Node parent=n.getParent();
  if (parent instanceof FunctionDef) {
    FunctionDef fn=(FunctionDef)parent;
    if (n == fn.name) {
      addStyle(n,StyleRun.Type.FUNCTION);
    }
 else     if (n == fn.kwargs || n == fn.varargs) {
      addStyle(n,StyleRun.Type.PARAMETER);
    }
    return true;
  }
  if (BUILTIN.matcher(n.getId()).matches()) {
    addStyle(n,StyleRun.Type.BUILTIN);
    return true;
  }
  return true;
}
