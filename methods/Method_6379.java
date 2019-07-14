public void increaseInlineRaiting(final int uid){
  if (!UserConfig.getInstance(currentAccount).suggestContacts) {
    return;
  }
  int dt;
  if (UserConfig.getInstance(currentAccount).botRatingLoadTime != 0) {
    dt=Math.max(1,((int)(System.currentTimeMillis() / 1000)) - UserConfig.getInstance(currentAccount).botRatingLoadTime);
  }
 else {
    dt=60;
  }
  TLRPC.TL_topPeer peer=null;
  for (int a=0; a < inlineBots.size(); a++) {
    TLRPC.TL_topPeer p=inlineBots.get(a);
    if (p.peer.user_id == uid) {
      peer=p;
      break;
    }
  }
  if (peer == null) {
    peer=new TLRPC.TL_topPeer();
    peer.peer=new TLRPC.TL_peerUser();
    peer.peer.user_id=uid;
    inlineBots.add(peer);
  }
  peer.rating+=Math.exp(dt / MessagesController.getInstance(currentAccount).ratingDecay);
  Collections.sort(inlineBots,(lhs,rhs) -> {
    if (lhs.rating > rhs.rating) {
      return -1;
    }
 else     if (lhs.rating < rhs.rating) {
      return 1;
    }
    return 0;
  }
);
  if (inlineBots.size() > 20) {
    inlineBots.remove(inlineBots.size() - 1);
  }
  savePeer(uid,1,peer.rating);
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.reloadInlineHints);
}
