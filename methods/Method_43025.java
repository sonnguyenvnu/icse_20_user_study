void checkError(BitbayBaseResponse response){
  if (!response.isSuccess()) {
    throw new ExchangeException(String.format("%d: %s",response.getCode(),response.getMessage()));
  }
}
