@OnMount static void onMount(ComponentContext c,SectionsRecyclerView sectionsRecycler,@Prop Binder<RecyclerView> binder,@Prop(optional=true) boolean hasFixedSize,@Prop(optional=true) boolean clipToPadding,@Prop(optional=true) int leftPadding,@Prop(optional=true) int rightPadding,@Prop(optional=true) int topPadding,@Prop(optional=true) int bottomPadding,@Prop(optional=true,resType=ResType.COLOR) @Nullable Integer refreshProgressBarBackgroundColor,@Prop(optional=true,resType=ResType.COLOR) int refreshProgressBarColor,@Prop(optional=true) boolean clipChildren,@Prop(optional=true) boolean nestedScrollingEnabled,@Prop(optional=true) int scrollBarStyle,@Prop(optional=true) RecyclerView.ItemDecoration itemDecoration,@Prop(optional=true) boolean horizontalFadingEdgeEnabled,@Prop(optional=true) boolean verticalFadingEdgeEnabled,@Prop(optional=true,resType=ResType.DIMEN_SIZE) int fadingEdgeLength,@Prop(optional=true) @IdRes int recyclerViewId,@Prop(optional=true) int overScrollMode,@Prop(optional=true,isCommonProp=true) CharSequence contentDescription){
  final RecyclerView recyclerView=sectionsRecycler.getRecyclerView();
  if (recyclerView == null) {
    throw new IllegalStateException("RecyclerView not found, it should not be removed from SwipeRefreshLayout");
  }
  recyclerView.setContentDescription(contentDescription);
  recyclerView.setHasFixedSize(hasFixedSize);
  recyclerView.setClipToPadding(clipToPadding);
  sectionsRecycler.setClipToPadding(clipToPadding);
  recyclerView.setPadding(leftPadding,topPadding,rightPadding,bottomPadding);
  recyclerView.setClipChildren(clipChildren);
  sectionsRecycler.setClipChildren(clipChildren);
  recyclerView.setNestedScrollingEnabled(nestedScrollingEnabled);
  sectionsRecycler.setNestedScrollingEnabled(nestedScrollingEnabled);
  recyclerView.setScrollBarStyle(scrollBarStyle);
  recyclerView.setHorizontalFadingEdgeEnabled(horizontalFadingEdgeEnabled);
  recyclerView.setVerticalFadingEdgeEnabled(verticalFadingEdgeEnabled);
  recyclerView.setFadingEdgeLength(fadingEdgeLength);
  recyclerView.setId(recyclerViewId);
  recyclerView.setOverScrollMode(overScrollMode);
  if (refreshProgressBarBackgroundColor != null) {
    sectionsRecycler.setProgressBackgroundColorSchemeColor(refreshProgressBarBackgroundColor);
  }
  sectionsRecycler.setColorSchemeColors(refreshProgressBarColor);
  if (itemDecoration != null) {
    recyclerView.addItemDecoration(itemDecoration);
  }
  binder.mount(recyclerView);
}
