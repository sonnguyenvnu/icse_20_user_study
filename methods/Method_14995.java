/** 
 * @param phone
 * @param requestCode
 * @param listener
 */
public static void checkRegister(String phone,int requestCode,OnHttpResponseListener listener){
  head(new JSONRequest(new Privacy().setPhone(phone)),requestCode,listener);
}
