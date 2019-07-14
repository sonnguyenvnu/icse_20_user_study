public void bind(SendBroadcastFragment.LinkInfo linkInfo,List<Uri> imageUris,boolean scrollImageListToEnd){
  boolean hasLink=linkInfo != null;
  ViewUtils.setVisibleOrGone(mLinkLayout,hasLink);
  if (hasLink) {
    String linkTitle=linkInfo.title;
    if (TextUtils.isEmpty(linkTitle)) {
      linkTitle=getContext().getString(R.string.broadcast_send_link_default_title);
    }
    mLinkTitleText.setText(linkTitle);
    mLinkDescriptionText.setText(ObjectUtils.firstNonNull(linkInfo.description,linkInfo.url));
    boolean hasLinkImage=linkInfo.imageUrl != null;
    ViewUtils.setVisibleOrGone(mLinkImage,hasLinkImage);
    if (hasLinkImage) {
      ImageUtils.loadImage(mLinkImage,linkInfo.imageUrl);
    }
    mLinkLayout.setOnClickListener(view -> UriHandler.open(linkInfo.url,view.getContext()));
  }
  boolean hasImage=!CollectionUtils.isEmpty(imageUris);
  boolean hasSingleImage=hasImage && imageUris.size() == 1;
  ViewUtils.setVisibleOrGone(mSingleImageLayout,hasSingleImage);
  if (hasSingleImage) {
    Uri imageUri=imageUris.get(0);
    mSingleImageLayout.setVisibility(VISIBLE);
    mSingleImageLayout.loadImage(imageUri);
    mSingleImageLayout.setOnClickListener(view -> {
      Context context=view.getContext();
      context.startActivity(GalleryActivity.makeIntent(imageUri,context));
    }
);
  }
  boolean hasImageList=hasImage && imageUris.size() > 1;
  ViewUtils.setVisibleOrGone(mImageListLayout,hasImageList);
  if (hasImageList) {
    mImageListDescriptionText.setText(mImageListDescriptionText.getContext().getString(R.string.broadcast_image_list_count_format,imageUris.size()));
    mImageListAdapter.replace(imageUris);
    mImageListAdapter.setOnItemClickListener((parent,itemView,item,position) -> {
      Context context=itemView.getContext();
      context.startActivity(GalleryActivity.makeIntent(imageUris,position,context));
    }
);
    if (scrollImageListToEnd) {
      mImageList.scrollToPosition(imageUris.size() - 1);
    }
  }
}
