private static Optional<BigInteger> asBigInteger(BigDecimal v){
  try {
    return Optional.of(v.toBigIntegerExact());
  }
 catch (  ArithmeticException e) {
    return Optional.empty();
  }
}
