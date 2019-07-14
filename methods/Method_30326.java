private boolean isChanged(){
  SimpleItemCollection collection=mItem.collection;
  ItemCollectionState state=getState();
  if (collection != null) {
    boolean equalsExtraState=mExtraState != null && state == mExtraState;
    boolean equalsCollectionState=state == collection.getState();
    if (!(equalsExtraState || equalsCollectionState)) {
      return true;
    }
  }
  if (state != ItemCollectionState.TODO) {
    float originalRating=collection != null && collection.rating != null ? collection.rating.getRatingBarRating() : 0;
    float rating=mRatingBar.getRating();
    if (rating != originalRating) {
      return true;
    }
  }
  List<String> tags=getTags();
  List<String> originalTags=collection != null ? collection.tags : Collections.emptyList();
  if (!ObjectsCompat.equals(tags,originalTags)) {
    return true;
  }
  String comment=mCommentEdit.getText().toString();
  String originalComment=collection != null ? MoreTextUtils.nullToEmpty(collection.comment) : "";
  if (!TextUtils.equals(comment,originalComment)) {
    return true;
  }
  return false;
}
