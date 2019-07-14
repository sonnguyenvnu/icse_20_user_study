@CallSuper @Override protected void onModelBound(@NonNull EpoxyViewHolder holder,@NonNull EpoxyModel<?> boundModel,int positionWithinCurrentModels,@Nullable EpoxyModel<?> previouslyBoundModel){
  int positionWithinList=positionWithinCurrentModels + lastBuiltLowerBound;
  if (pagedList != null && !pagedList.isEmpty()) {
    pagedList.loadAround(positionWithinList);
  }
  scrollingTowardsEnd=lastBoundPositionWithinList < positionWithinList;
  lastBoundPositionWithinList=positionWithinList;
  int prefetchDistance=config().prefetchDistance;
  final int distanceToEndOfPage=getAdapter().getItemCount() - positionWithinCurrentModels;
  final int distanceToStartOfPage=positionWithinCurrentModels;
  if ((distanceToEndOfPage < prefetchDistance && !hasBuiltLastItem() && scrollingTowardsEnd) || (distanceToStartOfPage < prefetchDistance && !hasBuiltFirstItem() && !scrollingTowardsEnd)) {
    requestModelBuild();
  }
}
