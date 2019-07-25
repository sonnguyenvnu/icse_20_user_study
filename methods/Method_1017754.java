/** 
 * @return a function converting a value of the base type into a {@link BigInteger}.
 */
protected Function<T,BigInteger> widen(){
  return n -> BigInteger.valueOf(n.longValue());
}
