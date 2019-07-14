/** 
 * ?????
 * @param id
 * @param requestCode
 * @param listener
 */
public static void setIsFriend(long id,boolean isFriend,int requestCode,OnHttpResponseListener listener){
  User user=application.getCurrentUser();
  if (user == null) {
    user=new User();
  }
  List<Long> list=new ArrayList<Long>();
  list.add(id);
  JSONObject userObject=new JSONObject(new User(user.getId()));
  userObject.put("contactIdList" + (isFriend ? "+" : "-"),list);
  put(new JSONRequest(USER_,userObject).setTag(USER_),requestCode,listener);
}
