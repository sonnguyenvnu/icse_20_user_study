public void removePeer(final int uid){
  for (int a=0; a < hints.size(); a++) {
    if (hints.get(a).peer.user_id == uid) {
      hints.remove(a);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.reloadHints);
      TLRPC.TL_contacts_resetTopPeerRating req=new TLRPC.TL_contacts_resetTopPeerRating();
      req.category=new TLRPC.TL_topPeerCategoryCorrespondents();
      req.peer=MessagesController.getInstance(currentAccount).getInputPeer(uid);
      deletePeer(uid,0);
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      }
);
      return;
    }
  }
}
