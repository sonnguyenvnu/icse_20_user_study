@TargetApi(23) private void registerNetworkCallbackV23(){
  ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
  NetworkRequest request=new NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED).build();
  networkCallback=new CapabilityValidatedCallback();
  connectivityManager.registerNetworkCallback(request,networkCallback);
}
