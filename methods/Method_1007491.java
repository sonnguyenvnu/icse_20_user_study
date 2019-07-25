public void execute() throws Pausable {
  BigInteger i=BigInteger.ZERO;
  BigInteger j=BigInteger.ONE;
  while (true) {
    yield(j);
    BigInteger f=i.add(j);
    i=j;
    j=f;
  }
}
