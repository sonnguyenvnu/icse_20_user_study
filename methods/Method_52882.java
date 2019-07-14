private Set<ASTElExpression> hasELInInnerElements(final ASTElement node){
  final Set<ASTElExpression> toReturn=new HashSet<>();
  final ASTContent content=node.getFirstChildOfType(ASTContent.class);
  if (content != null) {
    final List<ASTElement> innerElements=content.findChildrenOfType(ASTElement.class);
    for (    final ASTElement element : innerElements) {
      if (element.getName().equalsIgnoreCase(APEX_PARAM)) {
        final List<ASTAttribute> innerAttributes=element.findChildrenOfType(ASTAttribute.class);
        for (        ASTAttribute attrib : innerAttributes) {
          final List<ASTElExpression> elsInVal=attrib.findDescendantsOfType(ASTElExpression.class);
          for (          final ASTElExpression el : elsInVal) {
            if (startsWithSafeResource(el)) {
              continue;
            }
            if (doesElContainAnyUnescapedIdentifiers(el,Escaping.HTMLENCODE)) {
              toReturn.add(el);
            }
          }
        }
      }
    }
  }
  return toReturn;
}
