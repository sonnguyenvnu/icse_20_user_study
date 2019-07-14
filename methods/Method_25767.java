private static boolean isValidInitializerOrNotAByteBuffer(ExpressionTree receiver,VisitorState state){
  return BYTE_BUFFER_ALLOWED_INITIALIZERS_MATCHER.matches(receiver,state) || !BYTE_BUFFER_MATCHER.matches(receiver,state);
}
