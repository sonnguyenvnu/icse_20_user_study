/** 
 * ????
 * @param id
 * @param requestCode
 * @param listener
 */
public static void getMoment(long id,int requestCode,OnHttpResponseListener listener){
  JSONRequest request=new JSONRequest(new Moment(id));
  request.put(USER_,new JSONRequest(ID_AT,"/Moment/userId").setColumn(COLUMNS_USER));
  JSONRequest userItem=new JSONRequest();
  userItem.put(USER_,new JSONRequest(ID_IN + "@","Moment/praiseUserIdList").setColumn(COLUMNS_USER_SIMPLE));
  userItem.setQuery(JSONRequest.QUERY_ALL);
  request.putsAll(userItem.toArray(10,0,USER_));
  request.put("praiseCount@","/User[]/total");
  get(request,requestCode,listener);
}
