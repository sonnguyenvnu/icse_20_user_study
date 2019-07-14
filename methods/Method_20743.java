private static @NonNull List<LineItem> lineItemsFromPayload(final @NonNull AndroidPayPayload payload){
  return Observable.from(payload.cart().lineItems()).map(AndroidPayUtils::lineItemFromPayloadLineItem).toList().toBlocking().first();
}
