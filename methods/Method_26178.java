private Description checkCallArguments(List<? extends ExpressionTree> arguments,@Nullable MethodSymbol sym,VisitorState state){
  if (sym == null) {
    return Description.NO_MATCH;
  }
  for (int i=0; i < sym.getParameters().size(); ++i) {
    VarSymbol param=sym.getParameters().get(i);
    if (param.equals(sym.getParameters().last()) && sym.isVarArgs()) {
      break;
    }
    if (nullnessFromAnnotations(param).orElse(null) != Nullness.NONNULL) {
      continue;
    }
    ExpressionTree arg=arguments.get(i);
    Description result=checkExpression(arg,state,qual -> String.format("argument %s is %s null but %s expects it to be non-null",arg,qual,sym.getSimpleName()));
    if (result != null) {
      return result;
    }
  }
  return Description.NO_MATCH;
}
