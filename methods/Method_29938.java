private void bindRebroadcastedAttachmentImages(Broadcast broadcast,Broadcast rebroadcastedBroadcast){
  boolean hasRebroadcastedBroadcast=rebroadcastedBroadcast != null;
  ViewUtils.setVisibleOrGone(mRebroadcastedLayout,hasRebroadcastedBroadcast);
  if (hasRebroadcastedBroadcast) {
    setRebroadcastedAttachmentImagesLayoutOnClickListener(rebroadcastedBroadcast);
    ViewUtils.setVisibleOrGone(mRebroadcastedBroadcastDeletedText,rebroadcastedBroadcast.isDeleted);
    if (rebroadcastedBroadcast.isDeleted) {
      mRebroadcastedNameText.setText(null);
      mRebroadcastedActionText.setText(null);
      mRebroadcastedTextText.setText(null);
    }
 else {
      mRebroadcastedNameText.setText(rebroadcastedBroadcast.author.name);
      mRebroadcastedActionText.setText(rebroadcastedBroadcast.action);
      mRebroadcastedTextText.setText(rebroadcastedBroadcast.getTextWithEntities(mRebroadcastedTextText.getContext()));
    }
  }
 else {
    mRebroadcastedAttachmentImagesLayout.setOnClickListener(null);
  }
  Broadcast contentBroadcast=hasRebroadcastedBroadcast ? rebroadcastedBroadcast : broadcast;
  BroadcastAttachment attachment=contentBroadcast.attachment;
  List<? extends SizedImageItem> images=contentBroadcast.attachment != null && contentBroadcast.attachment.imageList != null ? contentBroadcast.attachment.imageList.images : contentBroadcast.images;
  if (attachment != null) {
    mAttachmentLayout.setVisibility(VISIBLE);
    mAttachmentTitleText.setText(attachment.title);
    CharSequence attachmentDescription=attachment.getTextWithEntities();
    if (TextUtils.isEmpty(attachmentDescription) && images.isEmpty()) {
      attachmentDescription=attachment.url;
    }
    mAttachmentDescriptionText.setText(attachmentDescription);
    boolean hasAttachmentImage=attachment.image != null && images.isEmpty();
    ViewUtils.setVisibleOrGone(mAttachmentImage,hasAttachmentImage);
    if (hasAttachmentImage) {
      ImageUtils.loadImage(mAttachmentImage,attachment.image);
    }
    String attachmentUrl=attachment.url;
    if (!TextUtils.isEmpty(attachmentUrl)) {
      mAttachmentLayout.setOnClickListener(view -> UriHandler.open(attachmentUrl,view.getContext()));
    }
 else {
      mAttachmentLayout.setOnClickListener(null);
    }
  }
 else {
    mAttachmentLayout.setVisibility(GONE);
  }
  boolean hasSingleImage=images.size() == 1;
  ViewUtils.setVisibleOrGone(mSingleImageLayout,hasSingleImage);
  if (hasSingleImage) {
    SizedImageItem image=images.get(0);
    mSingleImageLayout.loadImage(image);
    mSingleImageLayout.setOnClickListener(view -> {
      Context context=view.getContext();
      context.startActivity(GalleryActivity.makeIntent(image,context));
    }
);
  }
  boolean hasImageList=images.size() > 1;
  ViewUtils.setVisibleOrGone(mImageListLayout,hasImageList);
  if (hasImageList) {
    mImageListDescriptionText.setText(mImageListDescriptionText.getContext().getString(R.string.broadcast_image_list_count_format,images.size()));
    mImageListAdapter.replace(images);
    mImageListAdapter.setOnItemClickListener((parent,itemView,item,position) -> {
      Context context=itemView.getContext();
      context.startActivity(GalleryActivity.makeImageListIntent(images,position,context));
    }
);
  }
  boolean rebroadecastedAttachmentImagesVisible=hasRebroadcastedBroadcast || attachment != null || !images.isEmpty();
  ViewUtils.setVisibleOrGone(mRebroadcastedAttachmentImagesLayout,rebroadecastedAttachmentImagesVisible);
  ViewUtils.setVisibleOrGone(mRebroadcastedAttachmentImagesSpace,rebroadecastedAttachmentImagesVisible);
}
