/** 
 * ????
 * @param id
 * @param withMomentList
 * @param requestCode
 * @param listener
 */
public static void getUser(long id,boolean withMomentList,int requestCode,OnHttpResponseListener listener){
  JSONRequest request=new JSONRequest(new User(id));
  if (withMomentList) {
    request.putsAll(new JSONRequest(MOMENT_,new JSONRequest(USER_ID,id).setColumn("pictureList").setOrder(DATE_DOWN)).toArray(3,0,MOMENT_));
  }
  get(request,requestCode,listener);
}
