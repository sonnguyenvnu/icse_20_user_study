/** 
 * Compile-time equivalent of  {@code com.google.io.protocol.ProtocolSupport#isProto2MutableMessageClass}.
 */
public static boolean isProto2MutableMessageClass(VisitorState state,Type type){
  checkNotNull(type);
  return isAssignableTo(type,MUTABLE_MESSAGE_TYPE,state) && !isAssignableTo(type,PROTOCOL_MESSAGE_TYPE,state);
}
