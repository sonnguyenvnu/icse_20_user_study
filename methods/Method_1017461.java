@Override public void regist(WeixinMessageKey messageKey,Class<? extends WeixinMessage> messageClass){
  Class<?> clazz=match(messageKey);
  if (clazz != null) {
    throw new IllegalArgumentException("duplicate messagekey '" + messageKey + "' define for " + clazz);
  }
  messageClassMap.put(messageKey,messageClass);
}
