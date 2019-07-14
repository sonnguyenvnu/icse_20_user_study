/** 
 * ??????
 * @param range
 * @param id
 * @param search
 * @param count
 * @param page
 * @param requestCode
 * @param listener
 */
public static void getMomentList(int range,long id,com.alibaba.fastjson.JSONObject search,int count,int page,int requestCode,OnHttpResponseListener listener){
  JSONRequest request=new JSONRequest();
  JSONRequest moment=new JSONRequest();
switch (range) {
case RANGE_ALL:
    break;
case RANGE_SINGLE:
  moment.put(ID,id);
break;
case RANGE_USER:
moment.put(USER_ID,id);
break;
case RANGE_USER_FRIEND:
case RANGE_USER_CIRCLE:
if (application.isCurrentUser(id) == false) {
Log.e(TAG,"??????????!");
return;
}
apijson.demo.client.model.User currentUser=application.getCurrentUser();
if (currentUser == null) {
currentUser=new apijson.demo.client.model.User();
}
List<Long> list=currentUser.getContactIdList();
if (list == null) {
list=new ArrayList<Long>();
}
if (range == RANGE_USER_CIRCLE) {
list.add(currentUser.getId());
}
moment.put(USER_ID_IN,list);
break;
default :
break;
}
moment.setOrder(DATE_DOWN);
moment.putsAll(search);
request.put(MOMENT_,moment);
request.put(USER_,new JSONRequest(ID_AT,"/Moment/userId").setColumn(COLUMNS_USER));
JSONRequest userItem=new JSONRequest();
userItem.put(USER_,new JSONRequest(ID_IN + "@","[]/Moment/praiseUserIdList").setColumn(COLUMNS_USER_SIMPLE));
request.putsAll(userItem.toArray(10,0,USER_));
JSONRequest commentItem=new JSONRequest();
commentItem.put(COMMENT_,new JSONRequest(MOMENT_ID_AT,"[]/Moment/id").setOrder(DATE_UP));
commentItem.put(USER_,new JSONRequest(ID_AT,"/Comment/userId").setColumn(COLUMNS_USER_SIMPLE));
request.putsAll(commentItem.toArray(6,0,CommentItem.class.getSimpleName()));
get(request.toArray(count,page),requestCode,listener);
}
