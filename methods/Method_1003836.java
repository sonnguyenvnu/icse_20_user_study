private String[] serialize(ByteBuf sessionData) throws Exception {
  if (sessionData == null || sessionData.readableBytes() == 0) {
    return new String[0];
  }
  ByteBuf encrypted=null;
  ByteBuf digest=null;
  try {
    encrypted=crypto.encrypt(sessionData,bufferAllocator);
    String encryptedBase64=toBase64(encrypted);
    digest=signer.sign(encrypted.resetReaderIndex(),bufferAllocator);
    String digestBase64=toBase64(digest);
    String digestedBase64=encryptedBase64 + SESSION_SEPARATOR + digestBase64;
    if (digestedBase64.length() <= config.getMaxSessionCookieSize()) {
      return new String[]{digestedBase64};
    }
    int count=(int)Math.ceil((double)digestedBase64.length() / config.getMaxSessionCookieSize());
    String[] partitions=new String[count];
    for (int i=0; i < count; i++) {
      int from=i * config.getMaxSessionCookieSize();
      int to=Math.min(from + config.getMaxSessionCookieSize(),digestedBase64.length());
      partitions[i]=digestedBase64.substring(from,to);
    }
    return partitions;
  }
  finally {
    if (encrypted != null) {
      encrypted.release();
    }
    if (digest != null) {
      digest.release();
    }
  }
}
