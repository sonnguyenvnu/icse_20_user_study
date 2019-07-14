@Override public Description matchMethod(MethodTree tree,VisitorState state){
  if (!equalsMethodDeclaration().matches(tree,state)) {
    return NO_MATCH;
  }
  ClassSymbol classSymbol=getSymbol(tree).enclClass();
  Set<ComparisonSite> suspiciousComparisons=new HashSet<>();
  new TreeScanner<Void,Void>(){
    @Override public Void visitBinary(    BinaryTree node,    Void unused){
      if (node.getKind() == Kind.EQUAL_TO || node.getKind() == Kind.NOT_EQUAL_TO) {
        getDubiousComparison(classSymbol,node,node.getLeftOperand(),node.getRightOperand()).ifPresent(suspiciousComparisons::add);
      }
      return super.visitBinary(node,null);
    }
    @Override public Void visitMethodInvocation(    MethodInvocationTree node,    Void unused){
      if (COMPARISON_METHOD.matches(node,state)) {
        getDubiousComparison(classSymbol,node,node.getArguments().get(0),node.getArguments().get(1)).ifPresent(suspiciousComparisons::add);
      }
      if (instanceEqualsInvocation().matches(node,state)) {
        ExpressionTree receiver=getReceiver(node);
        if (receiver != null) {
          if (!receiver.toString().equals("super")) {
            getDubiousComparison(classSymbol,node,receiver,node.getArguments().get(0)).ifPresent(suspiciousComparisons::add);
          }
        }
      }
      return super.visitMethodInvocation(node,null);
    }
  }
.scan(tree,null);
  if (suspiciousComparisons.isEmpty()) {
    return NO_MATCH;
  }
  ImmutableSet<ComparisonPair> suspiciousPairs=suspiciousComparisons.stream().map(ComparisonSite::pair).collect(toImmutableSet());
  suspiciousComparisons.stream().filter(p -> !suspiciousPairs.contains(p.pair().reversed())).map(c -> buildDescription(c.tree()).setMessage(String.format("Suspicious comparison between `%s` and `%s`",c.pair().lhs(),c.pair().rhs())).build()).forEach(state::reportMatch);
  return NO_MATCH;
}
