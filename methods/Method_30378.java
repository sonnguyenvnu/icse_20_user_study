private void update(Music music,Rating rating,List<SimpleItemCollection> itemCollectionList,List<SimpleReview> reviewList,List<SimpleItemForumTopic> forumTopicList,List<CollectableItem> recommendationList,List<Doulist> relatedDoulistList){
  if (music != null) {
    super.updateWithSimpleItem(music);
  }
  if (music == null) {
    return;
  }
  if (!mBackdropBound) {
    if (ViewUtils.isInPortait(getActivity())) {
      ImageUtils.loadItemBackdropAndFadeIn(mBackdropImage,music.cover.getLargeUrl(),null);
      mBackdropLayout.setOnClickListener(view -> {
        Context context=view.getContext();
        context.startActivity(GalleryActivity.makeIntent(music.cover,context));
      }
);
    }
 else {
      mBackdropImage.setBackgroundColor(music.getThemeColor());
      ViewUtils.fadeIn(mBackdropImage);
    }
    mBackdropBound=true;
  }
  mAdapter.setData(new MusicDataAdapter.Data(music,rating,itemCollectionList,reviewList,forumTopicList,recommendationList,relatedDoulistList));
  if (mAdapter.getItemCount() > 0) {
    mContentStateLayout.setLoaded(true);
  }
}
