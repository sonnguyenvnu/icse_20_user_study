public static byte[] text2Binary(String payload) throws CharacterCodingException {
  return payload.getBytes(WebSocketFrame.TEXT_CHARSET);
}
