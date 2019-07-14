/** 
 * Gets the equivalent object with the passed code. <p>This is useful in case some currencies share codes, such that  {@link #getInstance(String)}may return the wrong currency.
 * @param code The code the returned object will evaluate to
 * @return A Currency representing the same currency but having the passed currency code
 * @throws IllegalArgumentException if the passed code is not listed for this currency
 */
public Currency getCodeCurrency(String code){
  if (code.equals(this.code))   return this;
  Currency currency=getInstance(code);
  if (currency.equals(this))   return currency;
  if (!attributes.codes.contains(code))   throw new IllegalArgumentException("Code not listed for this currency");
  return new Currency(code,attributes);
}
