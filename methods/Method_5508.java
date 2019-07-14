@TargetApi(19) @SuppressWarnings("ResourceType") private static CaptionStyleCompat createFromCaptionStyleV19(CaptioningManager.CaptionStyle captionStyle){
  return new CaptionStyleCompat(captionStyle.foregroundColor,captionStyle.backgroundColor,Color.TRANSPARENT,captionStyle.edgeType,captionStyle.edgeColor,captionStyle.getTypeface());
}
