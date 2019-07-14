public void setLink(TLRPC.BotInlineResult contextResult,boolean media,boolean divider,boolean shadow){
  needDivider=divider;
  needShadow=shadow;
  parentObject=inlineResult=contextResult;
  if (inlineResult != null) {
    documentAttach=inlineResult.document;
    photoAttach=inlineResult.photo;
  }
 else {
    documentAttach=null;
    photoAttach=null;
  }
  mediaWebpage=media;
  setAttachType();
  requestLayout();
  updateButtonState(false,false);
}
