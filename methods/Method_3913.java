private int distanceToCenter(@NonNull RecyclerView.LayoutManager layoutManager,@NonNull View targetView,OrientationHelper helper){
  final int childCenter=helper.getDecoratedStart(targetView) + (helper.getDecoratedMeasurement(targetView) / 2);
  final int containerCenter;
  if (layoutManager.getClipToPadding()) {
    containerCenter=helper.getStartAfterPadding() + helper.getTotalSpace() / 2;
  }
 else {
    containerCenter=helper.getEnd() / 2;
  }
  return childCenter - containerCenter;
}
