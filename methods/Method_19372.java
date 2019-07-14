@UiThread public void maybeTrackViewCreator(RenderInfo renderInfo){
  if (!renderInfo.rendersView()) {
    return;
  }
  ensureCustomViewTypeValidity(renderInfo);
  final ViewCreator viewCreator=renderInfo.getViewCreator();
  final int viewType;
  if (mViewCreatorToViewType.containsKey(viewCreator)) {
    viewType=mViewCreatorToViewType.get(viewCreator);
  }
 else {
    viewType=renderInfo.hasCustomViewType() ? renderInfo.getViewType() : mViewTypeCounter++;
    mViewTypeToViewCreator.put(viewType,viewCreator);
    mViewCreatorToViewType.put(viewCreator,viewType);
  }
  if (!renderInfo.hasCustomViewType()) {
    renderInfo.setViewType(viewType);
  }
}
