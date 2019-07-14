@Override public void removeAllImages(){
  mImageUris.clear();
  bindAttachmentLayout();
  updateBottomBar();
  mChanged=true;
}
