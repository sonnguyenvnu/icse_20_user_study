@Override public void onFailedDownload(String fileName,boolean canceled){
  updateButtonState(true,documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC,false);
}
