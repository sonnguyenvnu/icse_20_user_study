public void removeAllLocationSharings(){
  Utilities.stageQueue.postRunnable(() -> {
    for (int a=0; a < sharingLocations.size(); a++) {
      SharingLocationInfo info=sharingLocations.get(a);
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
    }
    sharingLocations.clear();
    sharingLocationsMap.clear();
    saveSharingLocation(null,2);
    stop(true);
    AndroidUtilities.runOnUIThread(() -> {
      sharingLocationsUI.clear();
      sharingLocationsMapUI.clear();
      stopService();
      NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsChanged);
    }
);
  }
);
}
