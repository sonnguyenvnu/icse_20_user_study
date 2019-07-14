public BiboxOrders getBiboxOpenOrders(){
  try {
    BiboxOrderPendingListCommandBody body=new BiboxOrderPendingListCommandBody(1,Integer.MAX_VALUE);
    BiboxOrderPendingListCommand cmd=new BiboxOrderPendingListCommand(body);
    BiboxSingleResponse<BiboxOrders> response=bibox.orderPendingList(BiboxCommands.of(cmd).json(),apiKey,signatureCreator);
    throwErrors(response);
    return response.get().getResult();
  }
 catch (  BiboxException e) {
    throw new ExchangeException(e.getMessage());
  }
}
