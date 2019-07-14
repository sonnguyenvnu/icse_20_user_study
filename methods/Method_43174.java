public BitflyerOrderbook getOrderbook() throws IOException {
  try {
    return bitflyer.getBoard();
  }
 catch (  BitflyerException e) {
    throw handleError(e);
  }
}
