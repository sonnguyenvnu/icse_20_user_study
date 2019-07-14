/** 
 * ??????????
 * @param requestCode
 * @param listener
 */
public static void getPrivacy(int requestCode,OnHttpResponseListener listener){
  gets(new JSONRequest(new Privacy(application.getCurrentUserId())).setTag(PRIVACY_),requestCode,listener);
}
