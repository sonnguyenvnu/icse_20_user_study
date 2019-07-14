private static ProfileResource newInstance(String userIdOrUid,me.zhanghai.android.douya.network.api.info.apiv2.SimpleUser simpleUser,User user){
  return new ProfileResource().setArguments(userIdOrUid,simpleUser,user);
}
