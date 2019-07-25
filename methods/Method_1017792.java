@Override protected Function<BigInteger,Short> narrow(){
  return BigInteger::shortValue;
}
