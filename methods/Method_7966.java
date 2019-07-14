private void calcBackgroundWidth(int maxWidth,int timeMore,int maxChildWidth){
  if (hasLinkPreview || hasOldCaptionPreview || hasGamePreview || hasInvoicePreview || maxWidth - currentMessageObject.lastLineWidth < timeMore || currentMessageObject.hasRtl) {
    totalHeight+=AndroidUtilities.dp(14);
    hasNewLineForTime=true;
    backgroundWidth=Math.max(maxChildWidth,currentMessageObject.lastLineWidth) + AndroidUtilities.dp(31);
    backgroundWidth=Math.max(backgroundWidth,(currentMessageObject.isOutOwner() ? timeWidth + AndroidUtilities.dp(17) : timeWidth) + AndroidUtilities.dp(31));
  }
 else {
    int diff=maxChildWidth - currentMessageObject.lastLineWidth;
    if (diff >= 0 && diff <= timeMore) {
      backgroundWidth=maxChildWidth + timeMore - diff + AndroidUtilities.dp(31);
    }
 else {
      backgroundWidth=Math.max(maxChildWidth,currentMessageObject.lastLineWidth + timeMore) + AndroidUtilities.dp(31);
    }
  }
}
