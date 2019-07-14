public List<BiboxDeals> getBiboxDeals(CurrencyPair currencyPair,Integer depth) throws IOException {
  try {
    BiboxResponse<List<BiboxDeals>> response=bibox.deals(DEALS_CMD,BiboxAdapters.toBiboxPair(currencyPair),depth);
    throwErrors(response);
    return response.getResult();
  }
 catch (  BiboxException e) {
    throw new ExchangeException(e.getMessage(),e);
  }
}
