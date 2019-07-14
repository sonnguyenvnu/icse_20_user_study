public void sendMessage(TLRPC.User user,long peer,MessageObject reply_to_msg,TLRPC.ReplyMarkup replyMarkup,HashMap<String,String> params){
  sendMessage(null,null,null,null,null,user,null,null,null,peer,null,reply_to_msg,null,true,null,null,replyMarkup,params,0,null);
}
