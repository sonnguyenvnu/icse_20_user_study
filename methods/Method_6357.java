public void markFaturedStickersByIdAsRead(final long id){
  if (!unreadStickerSets.contains(id) || readingStickerSets.contains(id)) {
    return;
  }
  readingStickerSets.add(id);
  TLRPC.TL_messages_readFeaturedStickers req=new TLRPC.TL_messages_readFeaturedStickers();
  req.id.add(id);
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
  }
);
  AndroidUtilities.runOnUIThread(() -> {
    unreadStickerSets.remove(id);
    readingStickerSets.remove(id);
    loadFeaturedHash=calcFeaturedStickersHash(featuredStickerSets);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.featuredStickersDidLoad);
    putFeaturedStickersToCache(featuredStickerSets,unreadStickerSets,loadFeaturedDate,loadFeaturedHash);
  }
,1000);
}
