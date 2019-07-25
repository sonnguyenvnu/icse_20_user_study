@Override public void post(String msg){
  WechatClient client=getClient();
  if (client.isLogin() && contact != null) {
    WechatMessage m=client.createMessage(0,msg,contact);
    client.sendMessage(m,contact);
  }
 else {
    error("??????????????????????????");
  }
}
