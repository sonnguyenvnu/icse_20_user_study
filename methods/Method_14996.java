/** 
 * ????
 * @param verify
 * @param phone
 * @param password
 * @param type
 * @param requestCode
 * @param listener
 */
public static void setPassword(String verify,String phone,String password,int type,int requestCode,OnHttpResponseListener listener){
  JSONRequest request=new JSONRequest();
  request.put(VERIFY,verify);
  Privacy privacy=new Privacy().setPhone(phone);
  if (type == Privacy.PASSWORD_TYPE_LOGIN) {
    privacy.setPassword(password);
  }
 else {
    privacy.setPayPassword(password);
  }
  request.put(privacy);
  HttpManager.getInstance().post(URL_BASE + "put/password",request,requestCode,listener);
}
