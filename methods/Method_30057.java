/** 
 * @deprecated Use {@link #write(User,boolean,Context)} instead.
 */
public void write(String userIdOrUid,boolean like,Context context){
  add(new FollowUserWriter(userIdOrUid,like,this),context);
}
