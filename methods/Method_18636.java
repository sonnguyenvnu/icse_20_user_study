private static void maybePerformIncrementalMountOnView(int scrollingParentWidth,int scrollingParentHeight,View view){
  final View underlyingView=view instanceof WrapperView ? ((WrapperView)view).getWrappedView() : view;
  if (!(underlyingView instanceof LithoView)) {
    return;
  }
  final LithoView lithoView=(LithoView)underlyingView;
  if (!lithoView.isIncrementalMountEnabled()) {
    return;
  }
  if (view != underlyingView && view.getHeight() != underlyingView.getHeight()) {
    throw new IllegalStateException("ViewDiagnosticsWrapper must be the same height as the underlying view");
  }
  final int translationX=(int)view.getTranslationX();
  final int translationY=(int)view.getTranslationY();
  final int top=view.getTop() + translationY;
  final int bottom=view.getBottom() + translationY;
  final int left=view.getLeft() + translationX;
  final int right=view.getRight() + translationX;
  if (left >= 0 && top >= 0 && right <= scrollingParentWidth && bottom <= scrollingParentHeight && lithoView.getPreviousMountBounds().width() == lithoView.getWidth() && lithoView.getPreviousMountBounds().height() == lithoView.getHeight()) {
    return;
  }
  final Rect rect=new Rect(Math.max(0,-left),Math.max(0,-top),Math.min(right,scrollingParentWidth) - left,Math.min(bottom,scrollingParentHeight) - top);
  if (rect.isEmpty()) {
    return;
  }
  lithoView.performIncrementalMount(rect,true);
}
