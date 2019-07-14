/** 
 * ?????3G??
 */
public static boolean is3rd(Context context){
  ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
  NetworkInfo networkINfo=cm.getActiveNetworkInfo();
  return networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE;
}
