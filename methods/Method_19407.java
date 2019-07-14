private int distanceToStart(@NonNull RecyclerView.LayoutManager layoutManager,@NonNull View targetView,OrientationHelper helper){
  final int childStart=helper.getDecoratedStart(targetView);
  final int containerStart=helper.getStartAfterPadding();
  return childStart - containerStart;
}
