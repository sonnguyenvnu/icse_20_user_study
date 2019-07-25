private boolean check(PeerConnection peer,InventoryMessage inventoryMessage){
  InventoryType type=inventoryMessage.getInventoryType();
  int size=inventoryMessage.getHashList().size();
  if (peer.isNeedSyncFromPeer() || peer.isNeedSyncFromUs()) {
    logger.warn("Drop inv: {} size: {} from Peer {}, syncFromUs: {}, syncFromPeer: {}.",type,size,peer.getInetAddress(),peer.isNeedSyncFromUs(),peer.isNeedSyncFromPeer());
    return false;
  }
  if (type.equals(InventoryType.TRX)) {
    int count=peer.getNodeStatistics().messageStatistics.tronInTrxInventoryElement.getCount(10);
    if (count > maxCountIn10s) {
      logger.warn("Drop inv: {} size: {} from Peer {}, Inv count: {} is overload.",type,size,peer.getInetAddress(),count);
      return false;
    }
    if (transactionsMsgHandler.isBusy()) {
      logger.warn("Drop inv: {} size: {} from Peer {}, transactionsMsgHandler is busy.",type,size,peer.getInetAddress());
      return false;
    }
  }
  return true;
}
