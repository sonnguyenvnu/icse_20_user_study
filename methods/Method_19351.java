/** 
 * If the recycler is circular, returns the position of the  {@link ComponentTreeHolder} that isused to render the item at given position. Otherwise, it returns the position passed as parameter, which is the same as the index of the  {@link ComponentTreeHolder}.
 */
@GuardedBy("this") private int getNormalizedPosition(int position){
  return mIsCircular ? position % mComponentTreeHolders.size() : position;
}
