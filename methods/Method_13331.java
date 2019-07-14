@Override protected boolean isHyperlinkEnabled(HyperlinkData hyperlinkData){
  return ((HyperlinkReferenceData)hyperlinkData).reference.enabled;
}
