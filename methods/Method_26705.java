public boolean reverify(Unifier unifier){
  return MoreObjects.firstNonNull(new PlaceholderVerificationVisitor(Collections2.transform(placeholder().requiredParameters(),Functions.forMap(arguments())),arguments().values()).scan(unifier.getBinding(placeholder().exprKey()),unifier),true);
}
