@Nullable private static String matchNaN(ExpressionTree tree){
  Symbol sym=ASTHelpers.getSymbol(tree);
  if (sym != null && sym.owner != null && sym.owner.asType() != null && sym.getSimpleName().contentEquals("NaN")) {
    if (sym.owner.getQualifiedName().contentEquals("java.lang.Double")) {
      return "Double";
    }
 else     if (sym.owner.getQualifiedName().contentEquals("java.lang.Float")) {
      return "Float";
    }
  }
  return null;
}
