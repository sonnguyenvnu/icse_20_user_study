@Nullable protected Optional<Unifier> typecheck(Unifier unifier,Inliner inliner,Warner warner,List<Type> expectedTypes,List<Type> actualTypes){
  try {
    ImmutableList<UTypeVar> freeTypeVars=freeTypeVars(unifier);
    infer(warner,inliner,inliner.<Type>inlineList(freeTypeVars),expectedTypes,inliner.symtab().voidType,actualTypes);
    for (    UTypeVar var : freeTypeVars) {
      Type instantiationForVar=infer(warner,inliner,inliner.<Type>inlineList(freeTypeVars),expectedTypes,var.inline(inliner),actualTypes);
      unifier.putBinding(var.key(),TypeWithExpression.create(instantiationForVar.getReturnType()));
    }
    if (!checkBounds(unifier,inliner,warner)) {
      return Optional.absent();
    }
    return Optional.of(unifier);
  }
 catch (  CouldNotResolveImportException e) {
    logger.log(FINE,"Failure to resolve an import",e);
    return Optional.absent();
  }
catch (  InferException e) {
    logger.log(FINE,"No valid instantiation found: " + e.getMessage());
    return Optional.absent();
  }
}
