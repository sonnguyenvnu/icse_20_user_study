@Override public Description matchSwitch(SwitchTree switchTree,VisitorState state){
  TypeSymbol switchType=((JCSwitch)switchTree).getExpression().type.tsym;
  if (switchType.getKind() != ElementKind.ENUM) {
    return NO_MATCH;
  }
  CaseTree caseBeforeDefault=null;
  CaseTree defaultCase=null;
  for (  CaseTree caseTree : switchTree.getCases()) {
    if (caseTree.getExpression() == null) {
      defaultCase=caseTree;
      break;
    }
 else {
      caseBeforeDefault=caseTree;
    }
  }
  if (caseBeforeDefault == null || defaultCase == null) {
    return NO_MATCH;
  }
  SetView<String> unhandledCases=unhandledCases(switchTree,switchType);
  if (unhandledCases.equals(ImmutableSet.of("UNRECOGNIZED"))) {
    return fixUnrecognized(switchTree,defaultCase,state);
  }
  if (unhandledCases.isEmpty()) {
    return fixDefault(switchTree,caseBeforeDefault,defaultCase,state);
  }
  return NO_MATCH;
}
