@Override protected Function<BigInteger,Long> narrow(){
  return BigInteger::longValue;
}
