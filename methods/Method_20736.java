public static @NonNull FullWalletRequest createFullWalletRequest(final @NonNull String googleTransactionId,final @NonNull AndroidPayPayload payload){
  return FullWalletRequest.newBuilder().setGoogleTransactionId(googleTransactionId).setCart(Cart.newBuilder().setCurrencyCode(payload.cart().currencyCode()).setTotalPrice(payload.cart().totalPrice()).setLineItems(lineItemsFromPayload(payload)).build()).build();
}
