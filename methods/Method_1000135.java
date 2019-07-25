private void check(PeerConnection peer,FetchInvDataMessage fetchInvDataMsg) throws P2pException {
  MessageTypes type=fetchInvDataMsg.getInvMessageType();
  if (type == MessageTypes.TRX) {
    for (    Sha256Hash hash : fetchInvDataMsg.getHashList()) {
      if (peer.getAdvInvSpread().getIfPresent(new Item(hash,InventoryType.TRX)) == null) {
        throw new P2pException(TypeEnum.BAD_MESSAGE,"not spread inv: {}" + hash);
      }
    }
    int fetchCount=peer.getNodeStatistics().messageStatistics.tronInTrxFetchInvDataElement.getCount(10);
    int maxCount=advService.getTrxCount().getCount(60);
    if (fetchCount > maxCount) {
      throw new P2pException(TypeEnum.BAD_MESSAGE,"maxCount: " + maxCount + ", fetchCount: " + fetchCount);
    }
  }
 else {
    boolean isAdv=true;
    for (    Sha256Hash hash : fetchInvDataMsg.getHashList()) {
      if (peer.getAdvInvSpread().getIfPresent(new Item(hash,InventoryType.BLOCK)) == null) {
        isAdv=false;
        break;
      }
    }
    if (isAdv) {
      MessageCount tronOutAdvBlock=peer.getNodeStatistics().messageStatistics.tronOutAdvBlock;
      tronOutAdvBlock.add(fetchInvDataMsg.getHashList().size());
      int outBlockCountIn1min=tronOutAdvBlock.getCount(60);
      int producedBlockIn2min=120_000 / ChainConstant.BLOCK_PRODUCED_INTERVAL;
      if (outBlockCountIn1min > producedBlockIn2min) {
        throw new P2pException(TypeEnum.BAD_MESSAGE,"producedBlockIn2min: " + producedBlockIn2min + ", outBlockCountIn1min: " + outBlockCountIn1min);
      }
    }
 else {
      if (!peer.isNeedSyncFromUs()) {
        throw new P2pException(TypeEnum.BAD_MESSAGE,"no need sync");
      }
      for (      Sha256Hash hash : fetchInvDataMsg.getHashList()) {
        long blockNum=new BlockId(hash).getNum();
        long minBlockNum=peer.getLastSyncBlockId().getNum() - 2 * NodeConstant.SYNC_FETCH_BATCH_NUM;
        if (blockNum < minBlockNum) {
          throw new P2pException(TypeEnum.BAD_MESSAGE,"minBlockNum: " + minBlockNum + ", blockNum: " + blockNum);
        }
        if (peer.getSyncBlockIdCache().getIfPresent(hash) != null) {
          throw new P2pException(TypeEnum.BAD_MESSAGE,new BlockId(hash).getString() + " is exist");
        }
        peer.getSyncBlockIdCache().put(hash,System.currentTimeMillis());
      }
    }
  }
}
