/** 
 * Returns a zero balance.
 * @param currency the balance currency.
 * @return a zero balance.
 */
public static Balance zero(Currency currency){
  return new Balance(currency,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO);
}
