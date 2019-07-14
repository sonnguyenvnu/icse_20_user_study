public static String binary2Text(byte[] payload) throws CharacterCodingException {
  return new String(payload,WebSocketFrame.TEXT_CHARSET);
}
