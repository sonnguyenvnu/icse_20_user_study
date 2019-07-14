public void markFaturedStickersAsRead(boolean query){
  if (unreadStickerSets.isEmpty()) {
    return;
  }
  unreadStickerSets.clear();
  loadFeaturedHash=calcFeaturedStickersHash(featuredStickerSets);
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.featuredStickersDidLoad);
  putFeaturedStickersToCache(featuredStickerSets,unreadStickerSets,loadFeaturedDate,loadFeaturedHash);
  if (query) {
    TLRPC.TL_messages_readFeaturedStickers req=new TLRPC.TL_messages_readFeaturedStickers();
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    }
);
  }
}
