public void broadcast(Message msg){
  if (fastForward) {
    return;
  }
  if (invToSpread.size() > maxSpreadSize) {
    logger.warn("Drop message, type: {}, ID: {}.",msg.getType(),msg.getMessageId());
    return;
  }
  Item item;
  if (msg instanceof BlockMessage) {
    BlockMessage blockMsg=(BlockMessage)msg;
    item=new Item(blockMsg.getMessageId(),InventoryType.BLOCK);
    logger.info("Ready to broadcast block {}",blockMsg.getBlockId().getString());
    blockMsg.getBlockCapsule().getTransactions().forEach(transactionCapsule -> {
      Sha256Hash tid=transactionCapsule.getTransactionId();
      invToSpread.remove(tid);
      trxCache.put(new Item(tid,InventoryType.TRX),new TransactionMessage(transactionCapsule.getInstance()));
    }
);
    blockCache.put(item,msg);
  }
 else   if (msg instanceof TransactionMessage) {
    TransactionMessage trxMsg=(TransactionMessage)msg;
    item=new Item(trxMsg.getMessageId(),InventoryType.TRX);
    trxCount.add();
    trxCache.put(item,new TransactionMessage(trxMsg.getTransactionCapsule().getInstance()));
  }
 else {
    logger.error("Adv item is neither block nor trx, type: {}",msg.getType());
    return;
  }
  invToSpread.put(item,System.currentTimeMillis());
  if (InventoryType.BLOCK.equals(item.getType())) {
    consumerInvToSpread();
  }
}
