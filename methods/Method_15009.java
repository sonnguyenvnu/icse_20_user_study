/** 
 * ????
 * @param change
 * @param payPassword
 * @param requestCode
 * @param listener
 */
public static void changeBalance(double change,String payPassword,int requestCode,OnHttpResponseListener listener){
  JSONObject privacy=new JSONObject(new Privacy(application.getCurrentUserId()).setPayPassword(payPassword));
  privacy.puts("balance+",change);
  JSONRequest request=new JSONRequest(PRIVACY_,privacy);
  HttpManager.getInstance().post(URL_BASE + "put/balance",request.setTag(PRIVACY_),requestCode,listener);
}
