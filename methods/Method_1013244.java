public final boolean match(OpApplNode oa,ModuleNode mn){
  ExprOrOpArgNode[] args=oa.getArgs();
  if (args == null || arity != args.length) {
    errors.addError(oa.getTreeNode().getLocation(),"Operator used with the wrong number of arguments.");
    return false;
  }
  return true;
}
