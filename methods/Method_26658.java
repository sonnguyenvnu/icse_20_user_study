@Override protected Choice<Unifier> defaultAction(Tree target,final Unifier unifier){
  if (target instanceof JCExpression) {
    JCExpression expression=(JCExpression)target;
    JCExpression currentBinding=unifier.getBinding(key());
    boolean isGood=trueOrNull(new TreeScanner<Boolean,Void>(){
      @Override public Boolean reduce(      @Nullable Boolean left,      @Nullable Boolean right){
        return trueOrNull(left) && trueOrNull(right);
      }
      @Override public Boolean visitIdentifier(      IdentifierTree ident,      Void v){
        Symbol identSym=ASTHelpers.getSymbol(ident);
        for (        ULocalVarIdent.Key key : Iterables.filter(unifier.getBindings().keySet(),ULocalVarIdent.Key.class)) {
          if (identSym == unifier.getBinding(key).getSymbol()) {
            return false;
          }
        }
        return true;
      }
    }
.scan(expression,null));
    if (!isGood) {
      return Choice.none();
    }
 else     if (currentBinding == null) {
      unifier.putBinding(key(),expression);
      return Choice.of(unifier);
    }
 else     if (currentBinding.toString().equals(expression.toString())) {
      return Choice.of(unifier);
    }
  }
  return Choice.none();
}
