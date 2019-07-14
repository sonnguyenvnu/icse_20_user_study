private boolean doesElContainAnyUnescapedIdentifiers(final ASTElExpression elExpression,EnumSet<Escaping> escapes){
  if (elExpression == null) {
    return false;
  }
  final Set<ASTIdentifier> nonEscapedIds=new HashSet<>();
  final List<ASTExpression> exprs=elExpression.findChildrenOfType(ASTExpression.class);
  for (  final ASTExpression expr : exprs) {
    if (innerContainsSafeFields(expr)) {
      continue;
    }
    final List<ASTIdentifier> ids=expr.findChildrenOfType(ASTIdentifier.class);
    for (    final ASTIdentifier id : ids) {
      boolean isEscaped=false;
      for (      Escaping e : escapes) {
        if (id.getImage().equalsIgnoreCase(e.toString())) {
          isEscaped=true;
          break;
        }
        if (e.equals(Escaping.ANY)) {
          for (          Escaping esc : Escaping.values()) {
            if (id.getImage().equalsIgnoreCase(esc.toString())) {
              isEscaped=true;
              break;
            }
          }
        }
      }
      if (!isEscaped) {
        nonEscapedIds.add(id);
      }
    }
  }
  return !nonEscapedIds.isEmpty();
}
