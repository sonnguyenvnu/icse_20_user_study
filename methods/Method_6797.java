public boolean canEditMessage(TLRPC.Chat chat){
  return canEditMessage(currentAccount,messageOwner,chat);
}
