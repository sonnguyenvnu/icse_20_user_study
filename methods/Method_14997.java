/** 
 * ?????
 * @param type
 * @param phone
 * @param verify
 * @param requestCode
 * @param listener
 */
public static void checkVerify(int type,String phone,String verify,int requestCode,OnHttpResponseListener listener){
  JSONRequest request=new JSONRequest();
  request.put(TYPE,type);
  request.put(PHONE,phone);
  request.put(VERIFY,verify);
  HttpManager.getInstance().post(URL_BASE + "heads/verify",request.setTag(VERIFY_),requestCode,listener);
}
