public void sendMessage(TLRPC.TL_document document,VideoEditedInfo videoEditedInfo,String path,long peer,MessageObject reply_to_msg,String caption,ArrayList<TLRPC.MessageEntity> entities,TLRPC.ReplyMarkup replyMarkup,HashMap<String,String> params,int ttl,Object parentObject){
  sendMessage(null,caption,null,null,videoEditedInfo,null,document,null,null,peer,path,reply_to_msg,null,true,null,entities,replyMarkup,params,ttl,parentObject);
}
