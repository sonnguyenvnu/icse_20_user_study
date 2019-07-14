private void updateBottomBar(){
  boolean isImagesEmpty=mImageUris.isEmpty();
  boolean isLinkEmpty=mLinkInfo == null;
  boolean isEmpty=isImagesEmpty && isLinkEmpty;
  boolean hasImage=!isImagesEmpty;
  boolean hasLink=!isLinkEmpty;
  ViewUtils.setVisibleOrGone(mAddImageButton,isEmpty);
  ViewUtils.setVisibleOrGone(mAddMoreImageButton,hasImage);
  ViewUtils.setVisibleOrGone(mRemoveAllImagesButton,hasImage);
  ViewUtils.setVisibleOrGone(mAddLinkButton,isEmpty);
  ViewUtils.setVisibleOrGone(mEditLinkButton,hasLink);
  ViewUtils.setVisibleOrGone(mRemoveLinkButton,hasLink);
}
