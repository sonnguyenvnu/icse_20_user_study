@Override public Description matchMethod(MethodTree tree,VisitorState state){
  MethodSymbol method=ASTHelpers.getSymbol(tree);
  if (method.isStatic() || method.isConstructor()) {
    return Description.NO_MATCH;
  }
  if (method.getModifiers().contains(Modifier.PUBLIC) || method.getModifiers().contains(Modifier.PRIVATE)) {
    List<MethodSymbol> overriddenMethods=getOverriddenMethods(state,method);
    if (!overriddenMethods.isEmpty()) {
      MethodSymbol nearestForOverrideMethod=overriddenMethods.get(0);
      String customMessage="must have protected or package-private visibility";
      if (nearestForOverrideMethod.equals(method)) {
        customMessage=MESSAGE_BASE + customMessage;
      }
 else {
        customMessage=String.format("Method overrides @ForOverride method %s.%s, so it %s",nearestForOverrideMethod.enclClass(),nearestForOverrideMethod,customMessage);
      }
      return buildDescription(tree).setMessage(customMessage).build();
    }
  }
  return Description.NO_MATCH;
}
