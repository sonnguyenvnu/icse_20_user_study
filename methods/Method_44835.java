/** 
 * @param order this should be a RippleLimitOrder object with the base and counter counterpartiespopulated for any currency other than XRP.
 */
@Override public String placeLimitOrder(final LimitOrder order) throws IOException {
  if (order instanceof RippleLimitOrder) {
    return placeOrder((RippleLimitOrder)order,ripple.validateOrderRequests());
  }
 else {
    throw new IllegalArgumentException("order must be of type: " + RippleLimitOrder.class.getName());
  }
}
