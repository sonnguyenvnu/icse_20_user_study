@Override public String requestDepositAddress(Currency currency,String... arguments) throws IOException {
  try {
    return getBittrexDepositAddress(currency.toString());
  }
 catch (  BittrexException e) {
    throw BittrexErrorAdapter.adapt(e);
  }
}
