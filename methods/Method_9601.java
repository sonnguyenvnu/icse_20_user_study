private void updateRows(boolean notify){
  rowCount=0;
  useProxyRow=rowCount++;
  useProxyDetailRow=rowCount++;
  connectionsHeaderRow=rowCount++;
  if (!SharedConfig.proxyList.isEmpty()) {
    proxyStartRow=rowCount;
    rowCount+=SharedConfig.proxyList.size();
    proxyEndRow=rowCount;
  }
 else {
    proxyStartRow=-1;
    proxyEndRow=-1;
  }
  proxyAddRow=rowCount++;
  proxyDetailRow=rowCount++;
  if (SharedConfig.currentProxy == null || SharedConfig.currentProxy.secret.isEmpty()) {
    boolean change=callsRow == -1;
    callsRow=rowCount++;
    callsDetailRow=rowCount++;
    if (!notify && change) {
      listAdapter.notifyItemChanged(proxyDetailRow);
      listAdapter.notifyItemRangeInserted(proxyDetailRow + 1,2);
    }
  }
 else {
    boolean change=callsRow != -1;
    callsRow=-1;
    callsDetailRow=-1;
    if (!notify && change) {
      listAdapter.notifyItemChanged(proxyDetailRow);
      listAdapter.notifyItemRangeRemoved(proxyDetailRow + 1,2);
    }
  }
  checkProxyList();
  if (notify && listAdapter != null) {
    listAdapter.notifyDataSetChanged();
  }
}
