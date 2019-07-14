public boolean needDelayRoundProgressDraw(){
  return (documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND || documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO) && currentMessageObject.type != 5 && MediaController.getInstance().isPlayingMessage(currentMessageObject);
}
