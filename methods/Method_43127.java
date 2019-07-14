public BigDecimal getMakerFee() throws IOException {
  return getBitfinexAccountInfos()[0].getMakerFees();
}
