/** 
 * @return whether this ComponentTree has a computed layout that will work for the given measurespecs.
 */
public synchronized boolean hasCompatibleLayout(int widthSpec,int heightSpec){
  return isCompatibleSpec(mMainThreadLayoutState,widthSpec,heightSpec) || isCompatibleSpec(mBackgroundLayoutState,widthSpec,heightSpec);
}
