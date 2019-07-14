public static boolean isDataPlan(){
  final ConnectivityManager connectivityManager=(ConnectivityManager)App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
  if (connectivityManager != null) {
    final android.net.NetworkInfo mobile=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
    return mobile.isConnectedOrConnecting();
  }
  return false;
}
