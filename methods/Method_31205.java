private BigInteger getOrZero(List<BigInteger> elements,int i){
  return i < elements.size() ? elements.get(i) : BigInteger.ZERO;
}
