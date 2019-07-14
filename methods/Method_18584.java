/** 
 * Registers an inner touch delegate for the given view with the specified expansion. It assumes the given view has its final bounds set.
 * @param index The drawing order index of the given view.
 * @param view The view to which touch expansion should be applied.
 * @param touchExpansion The expansion to be applied to each edge of the given view.
 */
void registerTouchExpansion(int index,View view,Rect touchExpansion){
  mDelegates.put(index,InnerTouchDelegate.acquire(view,touchExpansion));
}
