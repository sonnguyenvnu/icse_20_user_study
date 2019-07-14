private void removeImage(int position){
  mImageUris.remove(position);
  boolean removedImageAtEnd=position == mImageUris.size();
  bindAttachmentLayout(removedImageAtEnd);
  updateBottomBar();
  mChanged=true;
}
