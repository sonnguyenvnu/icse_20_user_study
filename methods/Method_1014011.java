/** 
 * Divide this QuantityType by the given  {@link QuantityType}.
 * @param state the {@link QuantityType} this QuantityType should be divided by.
 * @return the quotient from this QuantityType and the given {@link QuantityType}.
 */
public QuantityType<?> divide(QuantityType<?> state){
  return new QuantityType<>(this.quantity.divide(state.quantity));
}
