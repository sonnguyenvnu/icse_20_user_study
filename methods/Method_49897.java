/** 
 * Converts from  {@code carrierMessagingAppResult} to a platform result code.
 */
protected static int toSmsManagerResult(int carrierMessagingAppResult){
switch (carrierMessagingAppResult) {
case CarrierMessagingService.SEND_STATUS_OK:
    return Activity.RESULT_OK;
case CarrierMessagingService.SEND_STATUS_RETRY_ON_CARRIER_NETWORK:
  return SmsManager.MMS_ERROR_RETRY;
default :
return SmsManager.MMS_ERROR_UNSPECIFIED;
}
}
