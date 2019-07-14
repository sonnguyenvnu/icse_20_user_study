public Description describe(MethodInvocationTree t,VisitorState state){
  LiteralTree formatTree=(LiteralTree)t.getArguments().get(1);
  String fixedFormatString=BAD_PLACEHOLDER_REGEX.matcher(state.getSourceForNode((JCTree)formatTree)).replaceAll("%s");
  if (expectedArguments(fixedFormatString) == t.getArguments().size() - 2) {
    return describeMatch(formatTree,SuggestedFix.replace(formatTree,fixedFormatString));
  }
 else {
    int missing=t.getArguments().size() - 2 - expectedArguments(fixedFormatString);
    StringBuilder builder=new StringBuilder(fixedFormatString);
    builder.deleteCharAt(builder.length() - 1);
    builder.append(" [%s");
    for (int i=1; i < missing; i++) {
      builder.append(", %s");
    }
    builder.append("]\"");
    return describeMatch(t,SuggestedFix.replace(formatTree,builder.toString()));
  }
}
