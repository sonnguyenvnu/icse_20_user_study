public static void setUnreadFlags(TLRPC.Message message,int flag){
  message.unread=(flag & 1) == 0;
  message.media_unread=(flag & 2) == 0;
}
