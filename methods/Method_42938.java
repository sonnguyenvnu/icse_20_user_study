public String requestBiboxDepositAddress(Currency currency){
  try {
    BiboxSingleResponse<String> response=bibox.depositAddress(BiboxCommands.depositAddressCommand(currency.getCurrencyCode()).json(),apiKey,signatureCreator);
    throwErrors(response);
    return response.get().getResult();
  }
 catch (  BiboxException e) {
    throw new ExchangeException(e.getMessage());
  }
}
