/** 
 * Returns true if sending / downloading using the carrier app has failed and completes the action using platform API's, otherwise false.
 */
protected boolean maybeFallbackToRegularDelivery(int carrierMessagingAppResult){
  if (carrierMessagingAppResult == CarrierMessagingService.SEND_STATUS_RETRY_ON_CARRIER_NETWORK || carrierMessagingAppResult == CarrierMessagingService.DOWNLOAD_STATUS_RETRY_ON_CARRIER_NETWORK) {
    Timber.d("Sending/downloading MMS by IP failed.");
    mRequestManager.addSimRequest(MmsRequest.this);
    return true;
  }
 else {
    return false;
  }
}
