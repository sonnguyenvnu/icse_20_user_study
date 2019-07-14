/** 
 * Returns a list of the actual types to be matched. This consists of the types of the expressions bound to the @BeforeTemplate method parameters, concatenated with the types of the expressions bound to expression placeholders, sorted by the name of the placeholder method.
 */
protected List<Type> actualTypes(Inliner inliner){
  ArrayList<Type> result=new ArrayList<>();
  ImmutableList<String> argNames=expressionArgumentTypes().keySet().asList();
  for (int i=0; i < expressionArgumentTypes().size(); i++) {
    String argName=argNames.get(i);
    Optional<JCExpression> singleBinding=inliner.getOptionalBinding(new UFreeIdent.Key(argName));
    if (singleBinding.isPresent()) {
      result.add(singleBinding.get().type);
    }
 else {
      Optional<java.util.List<JCExpression>> exprs=inliner.getOptionalBinding(new URepeated.Key(argName));
      if (exprs.isPresent() && !exprs.get().isEmpty()) {
        Type[] exprTys=new Type[exprs.get().size()];
        for (int j=0; j < exprs.get().size(); j++) {
          exprTys[j]=exprs.get().get(j).type;
        }
        result.add(inliner.types().lub(List.from(exprTys)));
      }
    }
  }
  for (  PlaceholderExpressionKey key : Ordering.natural().immutableSortedCopy(Iterables.filter(inliner.bindings.keySet(),PlaceholderExpressionKey.class))) {
    result.add(inliner.getBinding(key).type);
  }
  return List.from(result);
}
