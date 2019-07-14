public void sendMessage(TLRPC.MessageMedia location,long peer,MessageObject reply_to_msg,TLRPC.ReplyMarkup replyMarkup,HashMap<String,String> params){
  sendMessage(null,null,location,null,null,null,null,null,null,peer,null,reply_to_msg,null,true,null,null,replyMarkup,params,0,null);
}
