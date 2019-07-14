public static boolean hasCompileTimeConstantAnnotation(VisitorState state,Symbol symbol){
  return hasAttribute(symbol,COMPILE_TIME_CONSTANT_ANNOTATION,state);
}
