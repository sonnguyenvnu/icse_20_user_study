/** 
 * ??
 * @param phone
 * @param password
 * @param name
 * @param sex
 * @param requestCode
 * @param listener
 */
public static void register(String verify,String phone,String password,String name,int sex,int requestCode,OnHttpResponseListener listener){
  JSONObject request=new JSONRequest(new Privacy(phone,password));
  request.puts(new User().setName(name).setSex(sex));
  request.puts(VERIFY,verify);
  HttpManager.getInstance().post(URL_BASE + "register",request,requestCode,listener);
}
