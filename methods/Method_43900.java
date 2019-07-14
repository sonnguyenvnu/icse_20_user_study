/** 
 * Gets the equivalent object with an ISO 4217 code, or if none a code which looks ISO compatible (starts with an X), or the constructed currency code if neither exist.
 */
public Currency getIso4217Currency(){
  if (attributes.isoCode == null)   return this;
  return getCodeCurrency(attributes.isoCode);
}
