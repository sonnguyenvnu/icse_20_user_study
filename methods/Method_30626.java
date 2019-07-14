protected ProfileResource setArguments(String userIdOrUid,me.zhanghai.android.douya.network.api.info.apiv2.SimpleUser simpleUser,User user){
  FragmentUtils.getArgumentsBuilder(this).putString(EXTRA_USER_ID_OR_UID,userIdOrUid).putParcelable(EXTRA_SIMPLE_USER,simpleUser).putParcelable(EXTRA_USER,user);
  return this;
}
