public BigDecimal readIntegerFixedPoint() throws IOException {
  int integer=data.readShort();
  int decimal=data.readUnsignedShort();
  return new BigDecimal(String.valueOf(integer) + "" + String.valueOf(decimal));
}
