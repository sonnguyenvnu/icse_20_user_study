private void checkLimitedFlags(ASTElement node,Object data){
switch (node.getName().toLowerCase(Locale.ROOT)) {
case IFRAME_CONST:
case APEXIFRAME_CONST:
case A_CONST:
    break;
default :
  return;
}
final List<ASTAttribute> attributes=node.findChildrenOfType(ASTAttribute.class);
boolean isEL=false;
final Set<ASTElExpression> toReport=new HashSet<>();
for (ASTAttribute attr : attributes) {
String name=attr.getName().toLowerCase(Locale.ROOT);
if (HREF.equalsIgnoreCase(name) || SRC.equalsIgnoreCase(name)) {
  boolean startingWithSlashText=false;
  final ASTText attrText=attr.getFirstDescendantOfType(ASTText.class);
  if (attrText != null) {
    if (0 == attrText.jjtGetChildIndex()) {
      String lowerCaseImage=attrText.getImage().toLowerCase(Locale.ROOT);
      if (lowerCaseImage.startsWith("/") || lowerCaseImage.startsWith("http") || lowerCaseImage.startsWith("mailto")) {
        startingWithSlashText=true;
      }
    }
  }
  if (!startingWithSlashText) {
    final List<ASTElExpression> elsInVal=attr.findDescendantsOfType(ASTElExpression.class);
    for (    ASTElExpression el : elsInVal) {
      if (startsWithSlashLiteral(el)) {
        break;
      }
      if (startsWithSafeResource(el)) {
        break;
      }
      if (doesElContainAnyUnescapedIdentifiers(el,Escaping.URLENCODE)) {
        isEL=true;
        toReport.add(el);
      }
    }
  }
}
}
if (isEL) {
for (ASTElExpression expr : toReport) {
  addViolation(data,expr);
}
}
}
