private Description fixUnrecognized(SwitchTree switchTree,CaseTree defaultCase,VisitorState state){
  List<? extends StatementTree> defaultStatements=defaultCase.getStatements();
  Description.Builder unrecognizedDescription=buildDescription(defaultCase).setMessage(DESCRIPTION_UNRECOGNIZED);
  if (trivialDefault(defaultStatements)) {
    SuggestedFix fix=SuggestedFix.replace(defaultCase,"case UNRECOGNIZED: \n // continue below");
    return unrecognizedDescription.addFix(fix).build();
  }
  String defaultContents=getDefaultCaseContents(defaultCase,defaultStatements,state);
  if (!canCompleteNormally(switchTree)) {
    SuggestedFix fix=SuggestedFix.builder().replace(defaultCase,"case UNRECOGNIZED: \n break;").postfixWith(switchTree,defaultContents).build();
    return unrecognizedDescription.addFix(fix).build();
  }
  SuggestedFix fix=SuggestedFix.replace(defaultCase,"case UNRECOGNIZED:" + defaultContents);
  if (!SuggestedFixes.compilesWithFix(fix,state)) {
    return NO_MATCH;
  }
  return unrecognizedDescription.addFix(fix).build();
}
