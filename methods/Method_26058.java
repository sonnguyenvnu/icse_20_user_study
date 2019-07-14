private static boolean matches(Type type,VisitorState state){
  if (state.errorProneOptions().isTestOnlyTarget()) {
    return false;
  }
  if (isVerboseLogMessage(state)) {
    return false;
  }
  return IS_LITE_PROTO.apply(type,state);
}
