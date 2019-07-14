private void ensureCustomViewTypeValidity(RenderInfo renderInfo){
  if (mCustomViewTypeEnabled && !renderInfo.hasCustomViewType()) {
    throw new IllegalStateException("If you enable custom viewTypes, you must provide a customViewType in ViewRenderInfo.");
  }
 else   if (!mCustomViewTypeEnabled && renderInfo.hasCustomViewType()) {
    throw new IllegalStateException("You must enable custom viewTypes to provide customViewType in ViewRenderInfo.");
  }
 else   if (mCustomViewTypeEnabled && mComponentViewType == renderInfo.getViewType()) {
    throw new IllegalStateException("CustomViewType cannot be the same as ComponentViewType.");
  }
}
