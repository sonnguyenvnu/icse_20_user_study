public boolean canEditMessageAnytime(TLRPC.Chat chat){
  return canEditMessageAnytime(currentAccount,messageOwner,chat);
}
