/** 
 * ????????
 */
public static boolean isNetworkConnected(Context context){
  try {
    if (context != null) {
      @SuppressWarnings("static-access") ConnectivityManager cm=(ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
      NetworkInfo info=cm.getActiveNetworkInfo();
      return info != null && info.isConnected();
    }
 else {
      return false;
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
    return false;
  }
}
