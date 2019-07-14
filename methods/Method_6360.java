private void processLoadStickersResponse(final int type,final TLRPC.TL_messages_allStickers res){
  final ArrayList<TLRPC.TL_messages_stickerSet> newStickerArray=new ArrayList<>();
  if (res.sets.isEmpty()) {
    processLoadedStickers(type,newStickerArray,false,(int)(System.currentTimeMillis() / 1000),res.hash);
  }
 else {
    final LongSparseArray<TLRPC.TL_messages_stickerSet> newStickerSets=new LongSparseArray<>();
    for (int a=0; a < res.sets.size(); a++) {
      final TLRPC.StickerSet stickerSet=res.sets.get(a);
      TLRPC.TL_messages_stickerSet oldSet=stickerSetsById.get(stickerSet.id);
      if (oldSet != null && oldSet.set.hash == stickerSet.hash) {
        oldSet.set.archived=stickerSet.archived;
        oldSet.set.installed=stickerSet.installed;
        oldSet.set.official=stickerSet.official;
        newStickerSets.put(oldSet.set.id,oldSet);
        newStickerArray.add(oldSet);
        if (newStickerSets.size() == res.sets.size()) {
          processLoadedStickers(type,newStickerArray,false,(int)(System.currentTimeMillis() / 1000),res.hash);
        }
        continue;
      }
      newStickerArray.add(null);
      final int index=a;
      TLRPC.TL_messages_getStickerSet req=new TLRPC.TL_messages_getStickerSet();
      req.stickerset=new TLRPC.TL_inputStickerSetID();
      req.stickerset.id=stickerSet.id;
      req.stickerset.access_hash=stickerSet.access_hash;
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        TLRPC.TL_messages_stickerSet res1=(TLRPC.TL_messages_stickerSet)response;
        newStickerArray.set(index,res1);
        newStickerSets.put(stickerSet.id,res1);
        if (newStickerSets.size() == res.sets.size()) {
          for (int a1=0; a1 < newStickerArray.size(); a1++) {
            if (newStickerArray.get(a1) == null) {
              newStickerArray.remove(a1);
              a1--;
            }
          }
          processLoadedStickers(type,newStickerArray,false,(int)(System.currentTimeMillis() / 1000),res.hash);
        }
      }
));
    }
  }
}
