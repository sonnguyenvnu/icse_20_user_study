/** 
 * ????
 * @param type
 * @param password ???phone?verify????old password
 * @param phone
 * @param verify
 * @param requestCode
 * @param listener
 */
public static void setPassword(int type,String password,String phone,String verify,int requestCode,OnHttpResponseListener listener){
  Privacy privacy=new Privacy(phone,password);
  JSONRequest request=new JSONRequest(privacy);
  request.put(VERIFY,verify);
  put(request.setTag(PRIVACY_),requestCode,listener);
}
