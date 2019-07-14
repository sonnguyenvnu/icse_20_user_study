public static boolean isMegagroup(TLRPC.Chat chat){
  return (chat instanceof TLRPC.TL_channel || chat instanceof TLRPC.TL_channelForbidden) && chat.megagroup;
}
