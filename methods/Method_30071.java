@Override protected FollowshipListActivityFragment onCreateActivityFragment(String userIdOrUid){
  return FollowingListActivityFragment.newInstance(userIdOrUid);
}
