@Override public Choice<State<JCVariableDecl>> visitVariable(final VariableTree node,State<?> state){
  return chooseSubtrees(state,s -> unifyExpression(node.getInitializer(),s),init -> maker().VarDef((JCModifiers)node.getModifiers(),(Name)node.getName(),(JCExpression)node.getType(),init));
}
