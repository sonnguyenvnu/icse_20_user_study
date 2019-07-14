@Override public Choice<Unifier> visitContinue(ContinueTree node,Unifier unifier){
  if (getLabel() == null) {
    return Choice.condition(node.getLabel() == null,unifier);
  }
 else {
    CharSequence boundName=unifier.getBinding(key());
    return Choice.condition(boundName != null && node.getLabel().contentEquals(boundName),unifier);
  }
}
