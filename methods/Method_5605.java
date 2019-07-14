private void applyStyleToOutput(Map<String,TtmlStyle> globalStyles,SpannableStringBuilder regionOutput,int start,int end){
  TtmlStyle resolvedStyle=TtmlRenderUtil.resolveStyle(style,styleIds,globalStyles);
  if (resolvedStyle != null) {
    TtmlRenderUtil.applyStylesToSpan(regionOutput,start,end,resolvedStyle);
  }
}
