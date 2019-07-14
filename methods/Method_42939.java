public BiboxPage<BiboxWithdrawal> requestBiboxWithdrawals(BiboxFundsCommandBody body){
  try {
    BiboxPagedResponses<BiboxWithdrawal> response=bibox.transferOutList(BiboxCommands.withdrawalsCommand(body).json(),apiKey,signatureCreator);
    throwErrors(response);
    BiboxPage<BiboxWithdrawal> page=response.get().getResult();
    return page;
  }
 catch (  BiboxException e) {
    throw new ExchangeException(e.getMessage());
  }
}
