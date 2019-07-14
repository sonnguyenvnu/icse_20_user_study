@Override public UIf visitIf(IfTree tree,Void v){
  return UIf.create(template(tree.getCondition()),template(tree.getThenStatement()),(tree.getElseStatement() == null) ? null : template(tree.getElseStatement()));
}
