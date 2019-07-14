private Description describe(ProtoField protoField,Collection<FieldWithValue> locations,VisitorState state){
  SuggestedFix.Builder fix=SuggestedFix.builder();
  long values=locations.stream().map(l -> state.getSourceForNode(l.getArgument())).distinct().count();
  if (values == 1) {
    for (    FieldWithValue field : Iterables.skip(locations,1)) {
      MethodInvocationTree method=field.getMethodInvocation();
      int startPos=state.getEndPosition(ASTHelpers.getReceiver(method));
      int endPos=state.getEndPosition(method);
      fix.replace(startPos,endPos,"");
    }
  }
  return buildDescription(locations.iterator().next().getArgument()).setMessage(String.format("%s was called %s with %s. Setting the same field multiple times is redundant, and " + "could mask a bug.",protoField,nTimes(locations.size()),values == 1 ? "the same argument" : "different arguments")).addFix(fix.build()).build();
}
