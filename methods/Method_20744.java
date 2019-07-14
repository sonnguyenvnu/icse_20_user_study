private static @NonNull LineItem lineItemFromPayloadLineItem(final @NonNull AndroidPayPayload.Cart.LineItem payloadLineItem){
  return LineItem.newBuilder().setCurrencyCode(payloadLineItem.currencyCode()).setDescription(payloadLineItem.description()).setQuantity(payloadLineItem.quantity()).setTotalPrice(payloadLineItem.totalPrice()).setUnitPrice(payloadLineItem.unitPrice()).setRole(LineItem.Role.REGULAR).build();
}
