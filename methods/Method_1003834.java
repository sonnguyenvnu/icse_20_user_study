@Override public Promise<ByteBuf> load(AsciiString sessionId){
  return Promise.sync(() -> {
    CookieStorage cookieStorage=getCookieStorage();
    if (!isValid(cookieStorage)) {
      return Unpooled.EMPTY_BUFFER;
    }
    setLastAccessTime(cookieStorage);
    return deserialize(cookieStorage.data);
  }
);
}
