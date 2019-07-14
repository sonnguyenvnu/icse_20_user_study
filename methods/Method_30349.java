private void update(Movie movie,Rating rating,List<Photo> photoList,List<SimpleCelebrity> celebrityList,List<ItemAwardItem> awardList,List<SimpleItemCollection> itemCollectionList,List<SimpleReview> reviewList,List<SimpleItemForumTopic> forumTopicList,List<CollectableItem> recommendationList,List<Doulist> relatedDoulistList){
  if (movie != null) {
    super.updateWithSimpleItem(movie);
  }
  if (movie == null || photoList == null) {
    return;
  }
  if (!mBackdropBound) {
    boolean hasTrailer=movie.trailer != null;
    mExcludeFirstPhoto=false;
    String backdropUrl=null;
    if (hasTrailer) {
      backdropUrl=movie.trailer.coverUrl;
      mBackdropLayout.setOnClickListener(view -> {
        UriHandler.open(movie.trailer.videoUrl,view.getContext());
      }
);
    }
 else     if (!photoList.isEmpty()) {
      backdropUrl=photoList.get(0).getLargeUrl();
      mExcludeFirstPhoto=true;
      mBackdropLayout.setOnClickListener(view -> {
        Context context=view.getContext();
        context.startActivity(GalleryActivity.makeImageListIntent(photoList,0,context));
      }
);
    }
 else     if (movie.poster != null) {
      backdropUrl=movie.poster.getLargeUrl();
      mBackdropLayout.setOnClickListener(view -> {
        Context context=view.getContext();
        context.startActivity(GalleryActivity.makeIntent(movie.poster,context));
      }
);
    }
 else     if (movie.cover != null) {
      backdropUrl=movie.cover.getLargeUrl();
      mBackdropLayout.setOnClickListener(view -> {
        Context context=view.getContext();
        context.startActivity(GalleryActivity.makeIntent(movie.cover,context));
      }
);
    }
    if (backdropUrl != null) {
      ImageUtils.loadItemBackdropAndFadeIn(mBackdropImage,backdropUrl,hasTrailer ? mBackdropPlayImage : null);
    }
 else {
      mBackdropImage.setBackgroundColor(movie.getThemeColor());
      ViewUtils.fadeIn(mBackdropImage);
    }
    mBackdropBound=true;
  }
  mAdapter.setData(new MovieDataAdapter.Data(movie,rating,photoList,mExcludeFirstPhoto,celebrityList,awardList,itemCollectionList,reviewList,forumTopicList,recommendationList,relatedDoulistList));
  if (mAdapter.getItemCount() > 0) {
    mContentStateLayout.setLoaded(true);
  }
}
