protected FollowshipUserListFragment setArguments(String userIdOrUid){
  FragmentUtils.getArgumentsBuilder(this).putString(EXTRA_USER_ID_OR_UID,userIdOrUid);
  return this;
}
