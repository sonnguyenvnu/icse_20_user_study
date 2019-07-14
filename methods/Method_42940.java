public BiboxPage<BiboxDeposit> requestBiboxDeposits(BiboxFundsCommandBody body){
  try {
    BiboxPagedResponses<BiboxDeposit> response=bibox.transferInList(BiboxCommands.depositsCommand(body).json(),apiKey,signatureCreator);
    throwErrors(response);
    BiboxPage<BiboxDeposit> page=response.get().getResult();
    return page;
  }
 catch (  BiboxException e) {
    throw new ExchangeException(e.getMessage());
  }
}
