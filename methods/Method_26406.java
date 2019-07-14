@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!matches(tree) || IN_JAVA_TIME.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  String idealReplacementCode="ZoneId.of(\"America/Los_Angeles\")";
  SuggestedFix.Builder fixBuilder=SuggestedFix.builder();
  String zoneIdName=SuggestedFixes.qualifyType(state,fixBuilder,"java.time.ZoneId");
  String replacementCode=zoneIdName + ".systemDefault()";
  ExpressionTree receiver=ASTHelpers.getReceiver(tree);
  boolean systemDefaultZoneClockMethod=CLOCK_MATCHER.matches(tree,state);
  String replacementMethod=systemDefaultZoneClockMethod ? "system" : ASTHelpers.getSymbol(tree).name.toString();
  if (receiver != null) {
    fixBuilder.replace(state.getEndPosition(receiver),state.getEndPosition(tree),"." + replacementMethod + "(" + replacementCode + ")");
  }
 else {
    if (systemDefaultZoneClockMethod) {
      fixBuilder.addStaticImport("java.time.Clock.systemDefaultZone");
    }
    fixBuilder.replace(tree,replacementMethod + "(" + replacementCode + ")");
  }
  return buildDescription(tree).setMessage(String.format("%s.%s is not allowed because it silently uses the system default time-zone. You " + "must pass an explicit time-zone (e.g., %s) to this method.",ASTHelpers.getSymbol(tree).owner.getSimpleName(),ASTHelpers.getSymbol(tree),idealReplacementCode)).addFix(fixBuilder.build()).build();
}
