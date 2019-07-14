public BiboxOrders getBiboxOrderHistory(){
  try {
    BiboxOrderPendingListCommandBody body=new BiboxOrderPendingListCommandBody(1,Integer.MAX_VALUE);
    BiboxOrderHistoryCommand cmd=new BiboxOrderHistoryCommand(body);
    BiboxSingleResponse<BiboxOrders> response=bibox.orderPendingList(BiboxCommands.of(cmd).json(),apiKey,signatureCreator);
    throwErrors(response);
    return response.get().getResult();
  }
 catch (  BiboxException e) {
    throw new ExchangeException(e.getMessage());
  }
}
