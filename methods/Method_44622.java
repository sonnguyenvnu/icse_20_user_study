public boolean cancelLykkeOrder(String id) throws IOException {
  try {
    lykke.cancelOrderById(id,apiKey);
    return true;
  }
 catch (  LykkeException e) {
    throw new ExchangeException(e.getMessage());
  }
}
