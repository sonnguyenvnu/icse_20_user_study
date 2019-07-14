@Override public Choice<Unifier> visitPrimitiveType(PrimitiveTypeTree tree,Unifier unifier){
  return Choice.condition(getPrimitiveTypeKind().equals(tree.getPrimitiveTypeKind()),unifier);
}
