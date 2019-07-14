/** 
 * Identifies methods with parameters that have a generic argument with Int, Long, or Double. If pre-conditions are met, it refactors them to the primitive specializations. <pre>PreConditions: (1): The method declaration has to be private (to do a safe refactoring) (2): Its parameters have to meet the following conditions: 2.1 Contain type java.util.function.Function 2.2 At least one argument type of the Function must be subtype of Number (3): All its invocations in the top-level enclosing class have to meet the following conditions as well: 3.1: lambda argument of Kind.LAMBDA_EXPRESSION 3.2: same as 2.1 3.3: same as 2.2 </pre> <pre> Refactoring Changes for matched methods: (1) Add the imports (2) Change the method signature to use utility function instead of Function (3) Find and change the 'apply' calls to the corresponding applyAsT </pre>
 */
@Override public Description matchMethod(MethodTree tree,VisitorState state){
  MethodSymbol methodSym=ASTHelpers.getSymbol(tree);
  if (!methodSym.getModifiers().contains(Modifier.PRIVATE)) {
    return Description.NO_MATCH;
  }
  ImmutableList<Tree> params=tree.getParameters().stream().filter(param -> hasFunctionAsArg(param,state)).filter(param -> isFunctionArgSubtypeOf(param,0,state.getTypeFromString(JAVA_LANG_NUMBER),state) || isFunctionArgSubtypeOf(param,1,state.getTypeFromString(JAVA_LANG_NUMBER),state)).collect(toImmutableList());
  if (params.isEmpty() || !methodCallsMeetConditions(methodSym,state)) {
    return Description.NO_MATCH;
  }
  SuggestedFix.Builder fixBuilder=SuggestedFix.builder();
  for (  Tree param : params) {
    getMappingForFunctionFromTree(param).ifPresent(mappedFunction -> {
      fixBuilder.addImport(getImportName(mappedFunction));
      fixBuilder.replace(param,getFunctionName(mappedFunction) + " " + ASTHelpers.getSymbol(param).name);
      refactorInternalApplyMethods(tree,fixBuilder,param,mappedFunction);
    }
);
  }
  return describeMatch(tree,fixBuilder.build());
}
