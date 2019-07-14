private void update(Game game,Rating rating,List<Photo> photoList,List<SimpleItemCollection> itemCollectionList,List<SimpleReview> gameGuideList,List<SimpleReview> reviewList,List<SimpleItemForumTopic> forumTopicList,List<CollectableItem> recommendationList,List<Doulist> relatedDoulistList){
  if (game != null) {
    super.updateWithSimpleItem(game);
  }
  if (game == null || photoList == null) {
    return;
  }
  if (!mBackdropBound) {
    mExcludeFirstPhoto=false;
    String backdropUrl=null;
    if (!photoList.isEmpty()) {
      backdropUrl=photoList.get(0).getLargeUrl();
      mExcludeFirstPhoto=true;
      mBackdropLayout.setOnClickListener(view -> {
        Context context=view.getContext();
        context.startActivity(GalleryActivity.makeImageListIntent(photoList,0,context));
      }
);
    }
 else     if (game.cover != null) {
      backdropUrl=game.cover.getLargeUrl();
      mBackdropLayout.setOnClickListener(view -> {
        Context context=view.getContext();
        context.startActivity(GalleryActivity.makeIntent(game.cover,context));
      }
);
    }
    if (backdropUrl != null) {
      ImageUtils.loadItemBackdropAndFadeIn(mBackdropImage,backdropUrl,null);
    }
 else {
      mBackdropImage.setBackgroundColor(game.getThemeColor());
      ViewUtils.fadeIn(mBackdropImage);
    }
    mBackdropBound=true;
  }
  mAdapter.setData(new GameDataAdapter.Data(game,rating,photoList,mExcludeFirstPhoto,itemCollectionList,gameGuideList,reviewList,forumTopicList,recommendationList,relatedDoulistList));
  if (mAdapter.getItemCount() > 0) {
    mContentStateLayout.setLoaded(true);
  }
}
