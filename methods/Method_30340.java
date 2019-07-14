@Override protected boolean isItemLoaded(int position){
  if (mData == null) {
    return false;
  }
  if (mData.movie == null) {
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
case CELEBRITY_LIST:
return mData.celebrityList != null;
case AWARD_LIST:
return mData.awardList != null;
case RATING:
return mData.rating != null;
case ITEM_COLLECTION_LIST:
return mData.itemCollectionList != null;
case REVIEW_LIST:
return mData.reviewList != null;
case FORUM_TOPIC_LIST:
return mData.forumTopicList != null;
case RECOMMENDATION_LIST:
return mData.recommendationList != null;
case RELATED_DOULIST_LIST:
return mData.relatedDoulistList != null;
default :
throw new IllegalArgumentException();
}
}
