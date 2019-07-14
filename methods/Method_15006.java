/** 
 * ??????
 * @param momentId
 * @param count
 * @param page
 * @param requestCode
 * @param listener
 */
public static void getCommentList(long momentId,int count,int page,int requestCode,OnHttpResponseListener listener){
  JSONRequest request=new JSONRequest();
  JSONObject comment=new JSONObject(new Comment().setMomentId(momentId));
  request.put(COMMENT_,comment.setOrder("toId+",DATE_UP));
  request.put(USER_,new JSONRequest(ID_AT,"/Comment/userId").setColumn(COLUMNS_USER));
  request=request.toArray(count,page);
  get(request,requestCode,listener);
}
