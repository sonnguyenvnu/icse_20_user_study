/** 
 * Performs incremental mount on the children views of the given ViewGroup.
 * @param scrollingViewParent ViewGroup container of views that will be incrementally mounted.
 */
public static void performIncrementalMount(ViewGroup scrollingViewParent){
  assertMainThread();
  final int viewGroupWidth=scrollingViewParent.getWidth();
  final int viewGroupHeight=scrollingViewParent.getHeight();
  for (int i=0; i < scrollingViewParent.getChildCount(); i++) {
    maybePerformIncrementalMountOnView(viewGroupWidth,viewGroupHeight,scrollingViewParent.getChildAt(i));
  }
}
