/** 
 * Returns a list of the expected types to be matched. This consists of the argument types from the @BeforeTemplate method, concatenated with the return types of expression placeholders, sorted by the name of the placeholder method.
 * @throws CouldNotResolveImportException if a referenced type could not be resolved
 */
protected List<Type> expectedTypes(Inliner inliner) throws CouldNotResolveImportException {
  ArrayList<Type> result=new ArrayList<>();
  ImmutableList<UType> types=expressionArgumentTypes().values().asList();
  ImmutableList<String> argNames=expressionArgumentTypes().keySet().asList();
  for (int i=0; i < argNames.size(); i++) {
    String argName=argNames.get(i);
    Optional<JCExpression> singleBinding=inliner.getOptionalBinding(new UFreeIdent.Key(argName));
    if (!singleBinding.isPresent()) {
      Optional<java.util.List<JCExpression>> exprs=inliner.getOptionalBinding(new URepeated.Key(argName));
      if (!exprs.isPresent() || exprs.get().isEmpty()) {
        continue;
      }
    }
    result.add(types.get(i).inline(inliner));
  }
  for (  PlaceholderExpressionKey key : Ordering.natural().immutableSortedCopy(Iterables.filter(inliner.bindings.keySet(),PlaceholderExpressionKey.class))) {
    result.add(key.method.returnType().inline(inliner));
  }
  return List.from(result);
}
