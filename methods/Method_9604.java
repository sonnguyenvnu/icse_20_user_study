@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.proxySettingsChanged) {
    updateRows(true);
  }
 else   if (id == NotificationCenter.didUpdateConnectionState) {
    int state=ConnectionsManager.getInstance(account).getConnectionState();
    if (currentConnectionState != state) {
      currentConnectionState=state;
      if (listView != null && SharedConfig.currentProxy != null) {
        int idx=SharedConfig.proxyList.indexOf(SharedConfig.currentProxy);
        if (idx >= 0) {
          RecyclerListView.Holder holder=(RecyclerListView.Holder)listView.findViewHolderForAdapterPosition(idx + proxyStartRow);
          if (holder != null) {
            TextDetailProxyCell cell=(TextDetailProxyCell)holder.itemView;
            cell.updateStatus();
          }
        }
      }
    }
  }
 else   if (id == NotificationCenter.proxyCheckDone) {
    if (listView != null) {
      SharedConfig.ProxyInfo proxyInfo=(SharedConfig.ProxyInfo)args[0];
      int idx=SharedConfig.proxyList.indexOf(proxyInfo);
      if (idx >= 0) {
        RecyclerListView.Holder holder=(RecyclerListView.Holder)listView.findViewHolderForAdapterPosition(idx + proxyStartRow);
        if (holder != null) {
          TextDetailProxyCell cell=(TextDetailProxyCell)holder.itemView;
          cell.updateStatus();
        }
      }
    }
  }
}
