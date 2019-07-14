public void setGif(TLRPC.Document document,boolean divider){
  needDivider=divider;
  needShadow=false;
  inlineResult=null;
  parentObject="gif" + document;
  documentAttach=document;
  photoAttach=null;
  mediaWebpage=true;
  setAttachType();
  requestLayout();
  updateButtonState(false,false);
}
