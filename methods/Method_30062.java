public boolean hasUserIdOrUid(String userIdOrUid){
  return mUser != null ? mUser.isIdOrUid(userIdOrUid) : TextUtils.equals(mUserIdOrUid,userIdOrUid);
}
