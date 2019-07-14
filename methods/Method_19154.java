@OnCreateLayout static @Nullable Component onCreateLayout(final ComponentContext c,@Prop Section section,@Prop(optional=true) Component loadingComponent,@Prop(optional=true) Component emptyComponent,@Prop(optional=true) Component errorComponent,@Prop(optional=true,varArg="onScrollListener") List<OnScrollListener> onScrollListeners,@Prop(optional=true) final LoadEventsHandler loadEventsHandler,@Prop(optional=true) boolean clipToPadding,@Prop(optional=true) boolean clipChildren,@Prop(optional=true) boolean nestedScrollingEnabled,@Prop(optional=true) int scrollBarStyle,@Prop(optional=true) ItemDecoration itemDecoration,@Prop(optional=true) ItemAnimator itemAnimator,@Prop(optional=true) @IdRes int recyclerViewId,@Prop(optional=true) int overScrollMode,@Prop(optional=true,resType=ResType.DIMEN_SIZE) int leftPadding,@Prop(optional=true,resType=ResType.DIMEN_SIZE) int rightPadding,@Prop(optional=true,resType=ResType.DIMEN_SIZE) int topPadding,@Prop(optional=true,resType=ResType.DIMEN_SIZE) int bottomPadding,@Prop(optional=true) EventHandler<TouchEvent> recyclerTouchEventHandler,@Prop(optional=true) boolean horizontalFadingEdgeEnabled,@Prop(optional=true) boolean verticalFadingEdgeEnabled,@Prop(optional=true,resType=ResType.DIMEN_SIZE) int fadingEdgeLength,@Prop(optional=true,resType=ResType.COLOR) @Nullable Integer refreshProgressBarBackgroundColor,@Prop(optional=true,resType=ResType.COLOR) int refreshProgressBarColor,@Prop(optional=true) LithoRecylerView.TouchInterceptor touchInterceptor,@Prop(optional=true) boolean setRootAsync,@Prop(optional=true) boolean disablePTR,@Prop(optional=true) RecyclerConfiguration recyclerConfiguration,@State(canUpdateLazily=true) boolean hasSetSectionTreeRoot,@State RecyclerCollectionEventsController internalEventsController,@State LoadingState loadingState,@State Binder<RecyclerView> binder,@State SectionTree sectionTree,@State RecyclerCollectionLoadEventsHandler recyclerCollectionLoadEventsHandler,@State SnapHelper snapHelper){
  recyclerCollectionLoadEventsHandler.setLoadEventsHandler(loadEventsHandler);
  if (hasSetSectionTreeRoot && setRootAsync) {
    sectionTree.setRootAsync(section);
  }
 else {
    RecyclerCollectionComponent.lazyUpdateHasSetSectionTreeRoot(c,true);
    sectionTree.setRoot(section);
  }
  final boolean isErrorButNoErrorComponent=loadingState == LoadingState.ERROR && (errorComponent == null);
  final boolean isEmptyButNoEmptyComponent=loadingState == LoadingState.EMPTY && (emptyComponent == null);
  final boolean shouldHideComponent=isEmptyButNoEmptyComponent || isErrorButNoErrorComponent;
  if (shouldHideComponent) {
    return null;
  }
  final boolean canPTR=recyclerConfiguration.getOrientation() != OrientationHelper.HORIZONTAL && !disablePTR;
  final Recycler.Builder recycler=Recycler.create(c).clipToPadding(clipToPadding).leftPadding(leftPadding).rightPadding(rightPadding).topPadding(topPadding).bottomPadding(bottomPadding).clipChildren(clipChildren).nestedScrollingEnabled(nestedScrollingEnabled).scrollBarStyle(scrollBarStyle).recyclerViewId(recyclerViewId).overScrollMode(overScrollMode).recyclerEventsController(internalEventsController).refreshHandler(!canPTR ? null : RecyclerCollectionComponent.onRefresh(c,sectionTree)).pullToRefresh(canPTR).itemDecoration(itemDecoration).horizontalFadingEdgeEnabled(horizontalFadingEdgeEnabled).verticalFadingEdgeEnabled(verticalFadingEdgeEnabled).fadingEdgeLengthDip(fadingEdgeLength).onScrollListener(new RecyclerCollectionOnScrollListener(internalEventsController)).onScrollListeners(onScrollListeners).refreshProgressBarBackgroundColor(refreshProgressBarBackgroundColor).refreshProgressBarColor(refreshProgressBarColor).snapHelper(snapHelper).touchInterceptor(touchInterceptor).binder(binder).itemAnimator(RecyclerCollectionComponentSpec.itemAnimator == itemAnimator ? new NoUpdateItemAnimator() : itemAnimator).flexShrink(0).touchHandler(recyclerTouchEventHandler);
  if (!binder.canMeasure() && !recyclerConfiguration.getRecyclerBinderConfiguration().isWrapContent()) {
    recycler.positionType(ABSOLUTE).positionPx(ALL,0);
  }
  final ContainerBuilder containerBuilder=Column.create(c).flexShrink(0).alignContent(FLEX_START).child(recycler);
  if (loadingState == LoadingState.LOADING && loadingComponent != null) {
    containerBuilder.child(Wrapper.create(c).delegate(loadingComponent).flexShrink(0).positionType(ABSOLUTE).positionPx(ALL,0));
  }
 else   if (loadingState == LoadingState.EMPTY) {
    containerBuilder.child(Wrapper.create(c).delegate(emptyComponent).flexShrink(0).positionType(ABSOLUTE).positionPx(ALL,0));
  }
 else   if (loadingState == LoadingState.ERROR) {
    containerBuilder.child(Wrapper.create(c).delegate(errorComponent).flexShrink(0).positionType(ABSOLUTE).positionPx(ALL,0));
  }
  return containerBuilder.build();
}
