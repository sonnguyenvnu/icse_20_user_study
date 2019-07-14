/** 
 * Got products list from play store, pop their details
 * @param responseCode
 * @param skuDetailsList
 */
private void popProductsList(int responseCode,List<SkuDetails> skuDetailsList){
  if (responseCode == BillingClient.BillingResponse.OK && skuDetailsList != null) {
    showPaymentsDialog(activity);
  }
}
