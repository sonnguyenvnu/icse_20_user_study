public void removeStickersSet(final Context context,final TLRPC.StickerSet stickerSet,final int hide,final BaseFragment baseFragment,final boolean showSettings){
  final int type=stickerSet.masks ? TYPE_MASK : TYPE_IMAGE;
  TLRPC.TL_inputStickerSetID stickerSetID=new TLRPC.TL_inputStickerSetID();
  stickerSetID.access_hash=stickerSet.access_hash;
  stickerSetID.id=stickerSet.id;
  if (hide != 0) {
    stickerSet.archived=hide == 1;
    for (int a=0; a < stickerSets[type].size(); a++) {
      TLRPC.TL_messages_stickerSet set=stickerSets[type].get(a);
      if (set.set.id == stickerSet.id) {
        stickerSets[type].remove(a);
        if (hide == 2) {
          stickerSets[type].add(0,set);
        }
 else {
          stickerSetsById.remove(set.set.id);
          installedStickerSetsById.remove(set.set.id);
          stickerSetsByName.remove(set.set.short_name);
        }
        break;
      }
    }
    loadHash[type]=calcStickersHash(stickerSets[type]);
    putStickersToCache(type,stickerSets[type],loadDate[type],loadHash[type]);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.stickersDidLoad,type);
    TLRPC.TL_messages_installStickerSet req=new TLRPC.TL_messages_installStickerSet();
    req.stickerset=stickerSetID;
    req.archived=hide == 1;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      AndroidUtilities.runOnUIThread(() -> {
        if (response instanceof TLRPC.TL_messages_stickerSetInstallResultArchive) {
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.needReloadArchivedStickers,type);
          if (hide != 1 && baseFragment != null && baseFragment.getParentActivity() != null) {
            StickersArchiveAlert alert=new StickersArchiveAlert(baseFragment.getParentActivity(),showSettings ? baseFragment : null,((TLRPC.TL_messages_stickerSetInstallResultArchive)response).sets);
            baseFragment.showDialog(alert.create());
          }
        }
      }
);
      AndroidUtilities.runOnUIThread(() -> loadStickers(type,false,false),1000);
    }
);
  }
 else {
    TLRPC.TL_messages_uninstallStickerSet req=new TLRPC.TL_messages_uninstallStickerSet();
    req.stickerset=stickerSetID;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      try {
        if (error == null) {
          if (stickerSet.masks) {
            Toast.makeText(context,LocaleController.getString("MasksRemoved",R.string.MasksRemoved),Toast.LENGTH_SHORT).show();
          }
 else {
            Toast.makeText(context,LocaleController.getString("StickersRemoved",R.string.StickersRemoved),Toast.LENGTH_SHORT).show();
          }
        }
 else {
          Toast.makeText(context,LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred),Toast.LENGTH_SHORT).show();
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      loadStickers(type,false,true);
    }
));
  }
}
