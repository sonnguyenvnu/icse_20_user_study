public BigDecimal getTakerFee() throws IOException {
  return getBitfinexAccountInfos()[0].getTakerFees();
}
