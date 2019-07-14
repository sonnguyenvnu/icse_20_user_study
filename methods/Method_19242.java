@OnMount static void onMount(final ComponentContext context,final HorizontalScrollLithoView horizontalScrollLithoView,@Prop(optional=true,resType=ResType.BOOL) boolean scrollbarEnabled,@Prop(optional=true) HorizontalScrollEventsController eventsController,@Prop(optional=true) final OnScrollChangeListener onScrollChangeListener,@State final ScrollPosition lastScrollPosition,@State ComponentTree childComponentTree,@FromBoundsDefined int componentWidth,@FromBoundsDefined int componentHeight,@FromBoundsDefined final YogaDirection layoutDirection){
  horizontalScrollLithoView.setHorizontalScrollBarEnabled(scrollbarEnabled);
  horizontalScrollLithoView.mount(childComponentTree,lastScrollPosition,onScrollChangeListener,componentWidth,componentHeight);
  final ViewTreeObserver viewTreeObserver=horizontalScrollLithoView.getViewTreeObserver();
  viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
    @Override public boolean onPreDraw(){
      horizontalScrollLithoView.getViewTreeObserver().removeOnPreDrawListener(this);
      if (lastScrollPosition.x == LAST_SCROLL_POSITION_UNSET) {
        if (layoutDirection == YogaDirection.RTL) {
          horizontalScrollLithoView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
        }
        lastScrollPosition.x=horizontalScrollLithoView.getScrollX();
      }
 else {
        horizontalScrollLithoView.setScrollX(lastScrollPosition.x);
      }
      return true;
    }
  }
);
  if (eventsController != null) {
    eventsController.setScrollableView(horizontalScrollLithoView);
  }
}
