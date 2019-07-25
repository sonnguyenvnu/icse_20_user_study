private ByteBuf deserialize(ImmutableList<Cookie> sessionCookies) throws Exception {
  if (sessionCookies.isEmpty()) {
    return Unpooled.EMPTY_BUFFER;
  }
  StringBuilder sessionCookie=new StringBuilder();
  for (  Cookie cookie : sessionCookies) {
    sessionCookie.append(cookie.value());
  }
  String[] parts=sessionCookie.toString().split(SESSION_SEPARATOR);
  if (parts.length != 2) {
    return Unpooled.buffer(0,0);
  }
  ByteBuf payload=null;
  ByteBuf digest=null;
  ByteBuf expectedDigest=null;
  ByteBuf decryptedPayload=null;
  try {
    payload=fromBase64(bufferAllocator,parts[0]);
    digest=fromBase64(bufferAllocator,parts[1]);
    expectedDigest=signer.sign(payload,bufferAllocator);
    if (ByteBufUtil.equals(digest,expectedDigest)) {
      decryptedPayload=crypto.decrypt(payload.resetReaderIndex(),bufferAllocator);
    }
 else {
      decryptedPayload=Unpooled.buffer(0,0);
    }
  }
  finally {
    if (payload != null) {
      payload.touch().release();
    }
    if (digest != null) {
      digest.release();
    }
    if (expectedDigest != null) {
      expectedDigest.release();
    }
  }
  return decryptedPayload.touch();
}
