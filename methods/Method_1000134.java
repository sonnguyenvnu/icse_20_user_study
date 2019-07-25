private void check(PeerConnection peer,BlockMessage msg) throws P2pException {
  Item item=new Item(msg.getBlockId(),InventoryType.BLOCK);
  if (!peer.getSyncBlockRequested().containsKey(msg.getBlockId()) && !peer.getAdvInvRequest().containsKey(item)) {
    throw new P2pException(TypeEnum.BAD_MESSAGE,"no request");
  }
  BlockCapsule blockCapsule=msg.getBlockCapsule();
  if (blockCapsule.getInstance().getSerializedSize() > maxBlockSize) {
    throw new P2pException(TypeEnum.BAD_MESSAGE,"block size over limit");
  }
  long gap=blockCapsule.getTimeStamp() - System.currentTimeMillis();
  if (gap >= BLOCK_PRODUCED_INTERVAL) {
    throw new P2pException(TypeEnum.BAD_MESSAGE,"block time error");
  }
}
