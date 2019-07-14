private void checkApexTagsThatSupportEscaping(ASTElement node,Object data){
  final List<ASTAttribute> attributes=node.findChildrenOfType(ASTAttribute.class);
  final Set<ASTElExpression> toReport=new HashSet<>();
  boolean isUnescaped=false;
  boolean isEL=false;
  boolean hasPlaceholders=false;
  for (  ASTAttribute attr : attributes) {
    String name=attr.getName().toLowerCase(Locale.ROOT);
switch (name) {
case ESCAPE:
case ITEM_ESCAPED:
      final ASTText text=attr.getFirstDescendantOfType(ASTText.class);
    if (text != null) {
      if (text.getImage().equalsIgnoreCase(FALSE)) {
        isUnescaped=true;
      }
    }
  break;
case VALUE:
case ITEM_VALUE:
final List<ASTElExpression> elsInVal=attr.findDescendantsOfType(ASTElExpression.class);
for (ASTElExpression el : elsInVal) {
if (startsWithSafeResource(el)) {
  continue;
}
if (doesElContainAnyUnescapedIdentifiers(el,Escaping.HTMLENCODE)) {
  isEL=true;
  toReport.add(el);
}
}
final ASTText textValue=attr.getFirstDescendantOfType(ASTText.class);
if (textValue != null) {
if (PLACEHOLDERS.matcher(textValue.getImage()).matches()) {
hasPlaceholders=true;
}
}
break;
default :
break;
}
}
if (hasPlaceholders && isUnescaped) {
for (ASTElExpression expr : hasELInInnerElements(node)) {
addViolation(data,expr);
}
}
if (isEL && isUnescaped) {
for (ASTElExpression expr : toReport) {
addViolation(data,expr);
}
}
}
