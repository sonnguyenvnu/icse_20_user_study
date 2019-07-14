/** 
 * Compile-time equivalent of  {@code com.google.io.protocol.ProtocolSupport#isProto2MessageClass}.
 */
public static boolean isProto2MessageClass(VisitorState state,Type type){
  checkNotNull(type);
  return isAssignableTo(type,MESSAGE_TYPE,state) && !isAssignableTo(type,PROTOCOL_MESSAGE_TYPE,state);
}
