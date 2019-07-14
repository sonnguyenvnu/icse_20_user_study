public BitflyerOrderbook getOrderbook(String productCode) throws IOException {
  try {
    return bitflyer.getBoard(productCode);
  }
 catch (  BitflyerException e) {
    throw handleError(e);
  }
}
