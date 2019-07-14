public void removeInline(final int uid){
  TLRPC.TL_topPeerCategoryPeers category=null;
  for (int a=0; a < inlineBots.size(); a++) {
    if (inlineBots.get(a).peer.user_id == uid) {
      inlineBots.remove(a);
      TLRPC.TL_contacts_resetTopPeerRating req=new TLRPC.TL_contacts_resetTopPeerRating();
      req.category=new TLRPC.TL_topPeerCategoryBotsInline();
      req.peer=MessagesController.getInstance(currentAccount).getInputPeer(uid);
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      }
);
      deletePeer(uid,1);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.reloadInlineHints);
      return;
    }
  }
}
