private boolean isMembersInjectionInvocation(MethodSymbol method,VisitorState state){
  if (method.getSimpleName().contentEquals("injectMembers")) {
    return false;
  }
  return isGeneratedBaseType(ASTHelpers.outermostClass(method),state,"dagger.MembersInjector");
}
