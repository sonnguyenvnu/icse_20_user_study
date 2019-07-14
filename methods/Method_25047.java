public static String binary2Text(byte[] payload,int offset,int length) throws CharacterCodingException {
  return new String(payload,offset,length,WebSocketFrame.TEXT_CHARSET);
}
