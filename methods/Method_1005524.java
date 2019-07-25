@Override public void start(OnGeocodingListener geocodingListener,OnReverseGeocodingListener reverseGeocodingListener){
  this.geocodingListener=geocodingListener;
  this.reverseGeocodingListener=reverseGeocodingListener;
  if (fromNameList.isEmpty() && fromLocationList.isEmpty()) {
    logger.w("No direct geocoding or reverse geocoding points added");
  }
 else {
    final IntentFilter directFilter=new IntentFilter(BROADCAST_DIRECT_GEOCODING_ACTION);
    final IntentFilter reverseFilter=new IntentFilter(BROADCAST_REVERSE_GEOCODING_ACTION);
    final Intent serviceIntent=new Intent(context,AndroidGeocodingService.class);
    serviceIntent.putExtra(LOCALE_ID,locale);
    if (!fromNameList.isEmpty()) {
      context.registerReceiver(directReceiver,directFilter);
      serviceIntent.putExtra(DIRECT_GEOCODING_ID,fromNameList);
    }
    if (!fromLocationList.isEmpty()) {
      context.registerReceiver(reverseReceiver,reverseFilter);
      serviceIntent.putExtra(REVERSE_GEOCODING_ID,fromLocationList);
    }
    context.startService(serviceIntent);
    fromNameList.clear();
    fromLocationList.clear();
  }
}
