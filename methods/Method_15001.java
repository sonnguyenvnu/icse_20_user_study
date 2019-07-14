/** 
 * ??????
 * @param range
 * @param id
 * @param search
 * @param idList
 * @param count
 * @param page
 * @param requestCode
 * @param listener
 */
public static void getUserList(int range,long id,com.alibaba.fastjson.JSONObject search,List<Long> idList,int count,int page,int requestCode,OnHttpResponseListener listener){
  JSONRequest request=new JSONRequest();
  JSONRequest userItem=new JSONRequest();
  if (idList != null) {
    userItem.put(ID_IN,idList);
  }
 else {
    apijson.demo.client.model.User currentUser=application.getCurrentUser();
    if (currentUser == null) {
      currentUser=new apijson.demo.client.model.User();
    }
switch (range) {
case RANGE_ALL:
      userItem.put("id!",currentUser.getId());
    userItem.setOrder(DATE_UP,(currentUser.getSex() == 0 ? "sex-" : ""));
  break;
case RANGE_SINGLE:
case RANGE_USER:
userItem.put(ID,id);
break;
case RANGE_USER_FRIEND:
case RANGE_USER_CIRCLE:
if (application.isCurrentUser(id) == false) {
Log.e(TAG,"??????????!");
return;
}
List<Long> list=currentUser.getContactIdList();
if (list == null) {
list=new ArrayList<Long>();
}
if (range == RANGE_USER_CIRCLE) {
list.add(currentUser.getId());
}
 else {
list.remove(currentUser.getId());
}
userItem.put(ID_IN,list);
userItem.setOrder("name+");
break;
case RANGE_MOMENT:
JSONObject moment=new JSONObject(new Moment(id));
moment.setColumn("praiseUserIdList");
request.put(MOMENT_,moment);
userItem.put(ID_IN + "@","Moment/praiseUserIdList");
break;
case RANGE_COMMENT:
JSONObject comment=new JSONObject(new Comment(id));
comment.setColumn(USER_ID);
request.put(COMMENT_,comment);
userItem.put(ID_AT,"Comment/userId");
break;
default :
break;
}
userItem.putsAll(search);
}
JSONRequest listRequest=new JSONRequest(USER_,userItem);
listRequest=listRequest.toArray(count,page,USER_);
request.putsAll(listRequest);
get(request,requestCode,listener);
}
