public static boolean canSendEmbed(TLRPC.Chat chat){
  return canUserDoAction(chat,ACTION_EMBED_LINKS);
}
