/** 
 * @param id
 * @param userId
 * @param requestCode
 * @param listener
 */
public static void deleteComment(long id,long userId,int requestCode,OnHttpResponseListener listener){
  delete(new JSONRequest(COMMENT_,new JSONObject(new Comment(id)).setRole(application.isCurrentUser(userId) ? RequestRole.OWNER.name() : RequestRole.ADMIN.name())).setTag(COMMENT_),requestCode,listener);
}
