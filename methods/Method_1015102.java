/** 
 * ????? userId?token??????
 * @param userId
 * @param token
 */
public void connect(String userId,String token){
  if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(token)) {
    throw new IllegalArgumentException("userId and token must be empty!");
  }
  this.userId=userId;
  this.token=token;
  this.userInfoCache=new LruCache<>(1024);
  this.groupMemberCache=new LruCache<>(1024);
  this.groupUserCache=new LruCache<>(1024);
  if (mClient != null) {
    try {
      mClient.connect(this.userId,this.token);
      SharedPreferences sp=gContext.getSharedPreferences("wildfirechat.config",Context.MODE_PRIVATE);
      sp.edit().putString("userId",userId).putString("token",token).commit();
      ;
    }
 catch (    RemoteException e) {
      e.printStackTrace();
    }
  }
}
