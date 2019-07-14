private static byte[] generatePayload(CloseCode code,String closeReason) throws CharacterCodingException {
  if (code != null) {
    byte[] reasonBytes=text2Binary(closeReason);
    byte[] payload=new byte[reasonBytes.length + 2];
    payload[0]=(byte)(code.getValue() >> 8 & 0xFF);
    payload[1]=(byte)(code.getValue() & 0xFF);
    System.arraycopy(reasonBytes,0,payload,2,reasonBytes.length);
    return payload;
  }
 else {
    return new byte[0];
  }
}
