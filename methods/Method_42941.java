public void requestBiboxWithdraw(BiboxTransferCommandBody body){
  try {
    BiboxSingleResponse<String> response=bibox.transfer(BiboxCommands.transferCommand(body).json(),apiKey,signatureCreator);
    throwErrors(response);
  }
 catch (  BiboxException e) {
    throw new ExchangeException(e.getMessage());
  }
}
