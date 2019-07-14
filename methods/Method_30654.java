@Override public void onChanged(int requestCode,User newUser,List<Broadcast> newBroadcastList,List<SimpleUser> newFollowingList,List<Diary> newDiaryList,List<UserItems> newUserItemList,List<SimpleReview> newReviewList){
  mAdapter.setData(new ProfileDataAdapter.Data(newUser,newBroadcastList,newFollowingList,newDiaryList,newUserItemList,newReviewList));
  if (mAdapter.getItemCount() > 0) {
    mContentStateLayout.setLoaded(true);
  }
  updateOptionsMenu();
}
