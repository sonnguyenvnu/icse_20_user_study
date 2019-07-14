public static boolean isMegagroup(TLRPC.Message message){
  return (message.flags & TLRPC.MESSAGE_FLAG_MEGAGROUP) != 0;
}
