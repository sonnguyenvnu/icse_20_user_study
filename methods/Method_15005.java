/** 
 * ????
 * @param content
 * @param requestCode
 * @param listener
 */
public static void addMoment(String content,int requestCode,OnHttpResponseListener listener){
  List<String> list=new ArrayList<String>();
  list.add("http://static.oschina.net/uploads/user/1218/2437072_100.jpg?t=1461076033000");
  list.add("http://common.cnblogs.com/images/icon_weibo_24.png");
  post(new JSONRequest(new Moment().setUserId(application.getCurrentUserId()).setContent(content).setPictureList(list)).setTag(MOMENT_),requestCode,listener);
}
