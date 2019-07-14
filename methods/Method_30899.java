protected UserResource setArguments(String userIdOrUid,SimpleUser simpleUser,User user){
  FragmentUtils.getArgumentsBuilder(this).putString(EXTRA_USER_ID_OR_UID,userIdOrUid).putParcelable(EXTRA_SIMPLE_USER,simpleUser).putParcelable(EXTRA_USER,user);
  return this;
}
