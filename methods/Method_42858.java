/** 
 * Helper method that obtains the payment method for a given currency, based on the payment-method information returned from abucoins.
 * @param currency
 * @return The type (string) of the payment method.
 * @throws IOException
 */
public String abucoinsPaymentMethodForCurrency(String currency) throws IOException {
  String method=null;
  AbucoinsPaymentMethod[] paymentMethods=getPaymentMethods();
  for (  AbucoinsPaymentMethod apm : paymentMethods) {
    if (apm.getCurrency().equals(currency)) {
      method=apm.getType();
      break;
    }
  }
  if (method == null)   logger.warn("Unable to determine the payment method suitable for " + currency + " this will likely lead to an error");
  return method;
}
