@Override public void setLink(LinkInfo linkInfo){
  mLinkInfo=linkInfo;
  bindAttachmentLayout();
  updateBottomBar();
  mChanged=true;
}
