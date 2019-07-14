private boolean startsWithSlashLiteral(final ASTElExpression elExpression){
  final ASTExpression expression=elExpression.getFirstChildOfType(ASTExpression.class);
  if (expression != null) {
    final ASTLiteral literal=expression.getFirstChildOfType(ASTLiteral.class);
    if (literal != null && literal.jjtGetChildIndex() == 0) {
      String lowerCaseLiteral=literal.getImage().toLowerCase(Locale.ROOT);
      if (lowerCaseLiteral.startsWith("'/") || lowerCaseLiteral.startsWith("\"/") || lowerCaseLiteral.startsWith("'http") || lowerCaseLiteral.startsWith("\"http")) {
        return true;
      }
    }
  }
  return false;
}
