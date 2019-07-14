private static boolean isDataNetworkAvailable(final Context context,final int subId){
  return !inAirplaneMode(context) && isMobileDataEnabled(context,subId);
}
