@Override public void onPurchasesUpdated(int responseCode,@Nullable List<Purchase> purchases){
  if (responseCode == BillingClient.BillingResponse.OK && purchases != null) {
    for (    Purchase purchase : purchases) {
      ConsumeResponseListener listener=(responseCode1,purchaseToken) -> {
        Toast.makeText(activity,R.string.donation_thanks,Toast.LENGTH_LONG).show();
      }
;
      billingClient.consumeAsync(purchase.getPurchaseToken(),listener);
    }
  }
}
