@Override public Operation store(AsciiString sessionId,ByteBuf sessionData){
  return Operation.of(() -> {
    maybeCleanup();
    ByteBuf retained=sessionData.retain().asReadOnly();
    cache.put(sessionId,retained);
  }
);
}
