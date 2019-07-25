/** 
 * Get or create big decimal value for the given big decimal.
 * @param dec the big decimal
 * @return the value
 */
public static ValueDecimal get(BigDecimal dec){
  if (BigDecimal.ZERO.equals(dec)) {
    return (ValueDecimal)ZERO;
  }
 else   if (BigDecimal.ONE.equals(dec)) {
    return (ValueDecimal)ONE;
  }
  return (ValueDecimal)Value.cache(new ValueDecimal(dec));
}
