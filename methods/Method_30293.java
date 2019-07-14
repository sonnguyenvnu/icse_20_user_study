@Override protected boolean isItemLoaded(int position){
  if (mData == null) {
    return false;
  }
  if (mData.game == null) {
    return false;
  }
switch (Items.values()[position]) {
case HEADER:
    return true;
case ITEM_COLLECTION:
  return true;
case BADGE_LIST:
return mData.rating != null;
case INTRODUCTION:
return true;
case PHOTO_LIST:
return mData.photoList != null;
case RATING:
return mData.rating != null;
case ITEM_COLLECTION_LIST:
return mData.itemCollectionList != null;
case GAME_GUIDE_LIST:
return mData.gameGuideList != null;
case REVIEW_LIST:
return mData.reviewList != null;
case RELATED_DOULIST_LIST:
return mData.relatedDoulistList != null;
default :
throw new IllegalArgumentException();
}
}
