protected static void throwExceptionIfError(CoinmateBaseResponse response) throws CoinmateException {
  if (response.isError()) {
    throw new CoinmateException(response.getErrorMessage());
  }
}
