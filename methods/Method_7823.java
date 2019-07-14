private void getStickers(){
  if (loadingStickers || endReached) {
    return;
  }
  loadingStickers=true;
  if (emptyView != null && !firstLoaded) {
    emptyView.showProgress();
  }
  if (listAdapter != null) {
    listAdapter.notifyDataSetChanged();
  }
  TLRPC.TL_messages_getArchivedStickers req=new TLRPC.TL_messages_getArchivedStickers();
  req.offset_id=sets.isEmpty() ? 0 : sets.get(sets.size() - 1).set.id;
  req.limit=15;
  req.masks=currentType == DataQuery.TYPE_MASK;
  int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error == null) {
      TLRPC.TL_messages_archivedStickers res=(TLRPC.TL_messages_archivedStickers)response;
      sets.addAll(res.sets);
      endReached=res.sets.size() != 15;
      loadingStickers=false;
      firstLoaded=true;
      if (emptyView != null) {
        emptyView.showTextView();
      }
      updateRows();
    }
  }
));
  ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
}
