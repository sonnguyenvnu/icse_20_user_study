private void check(PeerConnection peer,TransactionsMessage msg) throws P2pException {
  for (  Transaction trx : msg.getTransactions().getTransactionsList()) {
    Item item=new Item(new TransactionMessage(trx).getMessageId(),InventoryType.TRX);
    if (!peer.getAdvInvRequest().containsKey(item)) {
      throw new P2pException(TypeEnum.BAD_MESSAGE,"trx: " + msg.getMessageId() + " without request.");
    }
    peer.getAdvInvRequest().remove(item);
  }
}
