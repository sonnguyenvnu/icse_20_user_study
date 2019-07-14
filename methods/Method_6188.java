public BigDecimal readShortFixedPoint() throws IOException {
  int integer=data.readByte();
  int decimal=data.readUnsignedByte();
  return new BigDecimal(String.valueOf(integer) + "" + String.valueOf(decimal));
}
