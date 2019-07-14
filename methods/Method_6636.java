public void removeSharingLocation(final long did){
  Utilities.stageQueue.postRunnable(() -> {
    final SharingLocationInfo info=sharingLocationsMap.get(did);
    sharingLocationsMap.remove(did);
    if (info != null) {
      TLRPC.TL_messages_editMessage req=new TLRPC.TL_messages_editMessage();
      req.peer=MessagesController.getInstance(currentAccount).getInputPeer((int)info.did);
      req.id=info.mid;
      req.flags|=16384;
      req.media=new TLRPC.TL_inputMediaGeoLive();
      req.media.stopped=true;
      req.media.geo_point=new TLRPC.TL_inputGeoPointEmpty();
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
        if (error != null) {
          return;
        }
        MessagesController.getInstance(currentAccount).processUpdates((TLRPC.Updates)response,false);
      }
);
      sharingLocations.remove(info);
      saveSharingLocation(info,1);
      AndroidUtilities.runOnUIThread(() -> {
        sharingLocationsUI.remove(info);
        sharingLocationsMapUI.remove(info.did);
        if (sharingLocationsUI.isEmpty()) {
          stopService();
        }
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsChanged);
      }
);
      if (sharingLocations.isEmpty()) {
        stop(true);
      }
    }
  }
);
}
