private SmartClient create(String type){
  if (TYPE_QQ.equalsIgnoreCase(type)) {
    return new SmartQQClient();
  }
 else   if (TYPE_WECHAT.equalsIgnoreCase(type)) {
    return new WechatClient();
  }
  throw new UnsupportedOperationException("No client type " + type);
}
