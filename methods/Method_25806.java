@CheckReturnValue private boolean populateTypesToEnforce(MethodSymbol declaredMethod,Type calledMethodType,Type calledReceiverType,List<RequiredType> argumentTypeRequirements,VisitorState state){
  boolean foundAnyTypeToEnforce=false;
  List<VarSymbol> params=declaredMethod.params();
  for (int i=0; i < params.size(); i++) {
    VarSymbol varSymbol=params.get(i);
    CompatibleWith anno=ASTHelpers.getAnnotation(varSymbol,CompatibleWith.class);
    if (anno != null) {
      foundAnyTypeToEnforce=true;
      RequiredType requiredType=resolveRequiredTypeForThisCall(state,calledMethodType,calledReceiverType,declaredMethod,anno.value());
      if (declaredMethod.isVarArgs() && i == params.size() - 1) {
        if (i >= argumentTypeRequirements.size()) {
          break;
        }
 else {
          for (int j=i; j < argumentTypeRequirements.size(); j++) {
            argumentTypeRequirements.set(j,requiredType);
          }
        }
      }
 else {
        argumentTypeRequirements.set(i,requiredType);
      }
    }
  }
  return foundAnyTypeToEnforce;
}
