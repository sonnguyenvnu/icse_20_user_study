private static String findThatCallAndMakeCheckDescription(VisitorState state){
  MethodInvocationTree thatCall=findThatCall(state);
  if (thatCall == null) {
    return null;
  }
  return makeCheckDescription(getOnlyElement(thatCall.getArguments()),state);
}
