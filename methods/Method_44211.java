/** 
 * @param currency
 * @return
 */
public static String toSymbol(Currency currency){
  if (Currency.IOT.equals(currency)) {
    return "IOTA";
  }
  return currency.getSymbol();
}
