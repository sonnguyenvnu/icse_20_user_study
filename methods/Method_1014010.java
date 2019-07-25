/** 
 * Divide this QuantityType by the given value.
 * @param value the value this {@link QuantityType} should be divided by.
 * @return the quotient from this QuantityType and the given value.
 */
public QuantityType<?> divide(BigDecimal value){
  return new QuantityType<>(this.quantity.divide(value));
}
