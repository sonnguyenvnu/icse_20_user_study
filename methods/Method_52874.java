private void checkAllOnEventTags(ASTElement node,Object data){
  final List<ASTAttribute> attributes=node.findChildrenOfType(ASTAttribute.class);
  boolean isEL=false;
  final Set<ASTElExpression> toReport=new HashSet<>();
  for (  ASTAttribute attr : attributes) {
    String name=attr.getName().toLowerCase(Locale.ROOT);
    if (ON_EVENT.matcher(name).matches()) {
      final List<ASTElExpression> elsInVal=attr.findDescendantsOfType(ASTElExpression.class);
      for (      ASTElExpression el : elsInVal) {
        if (startsWithSafeResource(el)) {
          continue;
        }
        if (doesElContainAnyUnescapedIdentifiers(el,EnumSet.of(Escaping.ANY))) {
          isEL=true;
          toReport.add(el);
        }
      }
    }
  }
  if (isEL) {
    for (    ASTElExpression expr : toReport) {
      addViolation(data,expr);
    }
  }
}
