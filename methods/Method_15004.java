/** 
 * ???
 * @param id
 * @param toPraise
 * @param requestCode
 * @param listener
 */
public static void praiseMoment(long id,boolean toPraise,int requestCode,OnHttpResponseListener listener){
  JSONObject data=new JSONObject(new Moment(id));
  List<Long> list=new ArrayList<Long>();
  list.add(application.getCurrentUserId());
  data.puts("praiseUserIdList" + (toPraise ? "+" : "-"),list);
  put(new JSONRequest(MOMENT_,data).setTag(MOMENT_),requestCode,listener);
}
