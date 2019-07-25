@Override protected Function<BigInteger,Byte> narrow(){
  return BigInteger::byteValue;
}
