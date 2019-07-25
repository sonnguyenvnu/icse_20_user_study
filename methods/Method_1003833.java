@Override public Operation store(AsciiString sessionId,ByteBuf sessionData){
  return Operation.of(() -> {
    CookieStorage cookieStorage=getCookieStorage();
    int oldSessionCookiesCount=cookieStorage.data.size();
    String[] sessionCookiePartitions=serialize(sessionData);
    for (int i=0; i < sessionCookiePartitions.length; i++) {
      addCookie(config.getSessionCookieName() + "_" + i,sessionCookiePartitions[i]);
    }
    for (int i=sessionCookiePartitions.length; i < oldSessionCookiesCount; i++) {
      invalidateCookie(config.getSessionCookieName() + "_" + i);
    }
    setLastAccessTime(cookieStorage);
  }
);
}
