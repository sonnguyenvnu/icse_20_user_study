@Override protected boolean isItemLoaded(int position){
  if (mData == null) {
    return false;
  }
  if (mData.user == null) {
    return false;
  }
switch (Items.values()[position]) {
case INTRODUCTION:
case BROADCASTS:
    return mData.broadcastList != null;
case FOLLOWSHIP:
  return mData.followingList != null;
case DIARIES:
return mData.diaryList != null;
case BOOKS:
return mData.userItemList != null;
case MOVIES:
return mData.userItemList != null;
case MUSIC:
return mData.userItemList != null;
case REVIEWS:
return mData.reviewList != null;
default :
throw new IllegalArgumentException();
}
}
