public static boolean isForwardedMessage(TLRPC.Message message){
  return (message.flags & TLRPC.MESSAGE_FLAG_FWD) != 0 && message.fwd_from != null;
}
