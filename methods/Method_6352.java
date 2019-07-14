private void processLoadedFeaturedStickers(final ArrayList<TLRPC.StickerSetCovered> res,final ArrayList<Long> unreadStickers,final boolean cache,final int date,final int hash){
  AndroidUtilities.runOnUIThread(() -> {
    loadingFeaturedStickers=false;
    featuredStickersLoaded=true;
  }
);
  Utilities.stageQueue.postRunnable(() -> {
    if (cache && (res == null || Math.abs(System.currentTimeMillis() / 1000 - date) >= 60 * 60) || !cache && res == null && hash == 0) {
      AndroidUtilities.runOnUIThread(() -> {
        if (res != null && hash != 0) {
          loadFeaturedHash=hash;
        }
        loadFeaturedStickers(false,false);
      }
,res == null && !cache ? 1000 : 0);
      if (res == null) {
        return;
      }
    }
    if (res != null) {
      try {
        final ArrayList<TLRPC.StickerSetCovered> stickerSetsNew=new ArrayList<>();
        final LongSparseArray<TLRPC.StickerSetCovered> stickerSetsByIdNew=new LongSparseArray<>();
        for (int a=0; a < res.size(); a++) {
          TLRPC.StickerSetCovered stickerSet=res.get(a);
          stickerSetsNew.add(stickerSet);
          stickerSetsByIdNew.put(stickerSet.set.id,stickerSet);
        }
        if (!cache) {
          putFeaturedStickersToCache(stickerSetsNew,unreadStickers,date,hash);
        }
        AndroidUtilities.runOnUIThread(() -> {
          unreadStickerSets=unreadStickers;
          featuredStickerSetsById=stickerSetsByIdNew;
          featuredStickerSets=stickerSetsNew;
          loadFeaturedHash=hash;
          loadFeaturedDate=date;
          loadStickers(TYPE_FEATURED,true,false);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.featuredStickersDidLoad);
        }
);
      }
 catch (      Throwable e) {
        FileLog.e(e);
      }
    }
 else     if (!cache) {
      AndroidUtilities.runOnUIThread(() -> loadFeaturedDate=date);
      putFeaturedStickersToCache(null,null,date,0);
    }
  }
);
}
