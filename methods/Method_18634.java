/** 
 * Performs incremental mount on all  {@link LithoView}s within the given View. 
 */
public static void incrementallyMountLithoViews(View view){
  if (view instanceof LithoView && ((LithoView)view).isIncrementalMountEnabled()) {
    ((LithoView)view).performIncrementalMount();
  }
 else   if (view instanceof ViewGroup) {
    for (int i=0, size=((ViewGroup)view).getChildCount(); i < size; i++) {
      final View child=((ViewGroup)view).getChildAt(i);
      incrementallyMountLithoViews(child);
    }
  }
}
