/** 
 * The expected counterparty transfer fee for an order that results in a transfer of the supplied amount of currency. The fee rate is payable when sending the currency (not receiving it) and it set by the issuing counterparty. The rate may be zero. Transfer fees are not applicable to sending XRP. More details can be found <a href="https://wiki.ripple.com/Transit_Fee">here</a>.
 * @return transfer fee of the supplied currency
 */
public static BigDecimal getExpectedTransferFee(final ITransferFeeSource transferFeeSource,final String counterparty,final String currency,final BigDecimal quantity,final OrderType type) throws IOException {
  if (currency.equals("XRP")) {
    return BigDecimal.ZERO;
  }
  if (counterparty.isEmpty()) {
    return BigDecimal.ZERO;
  }
  final BigDecimal transferFeeRate=transferFeeSource.getTransferFeeRate(counterparty);
  if ((transferFeeRate.compareTo(BigDecimal.ZERO) > 0) && (type == OrderType.ASK)) {
    return quantity.multiply(transferFeeRate).abs();
  }
 else {
    return BigDecimal.ZERO;
  }
}
