/** 
 * Processes the fact that, as far as we can tell, the data set has completely changed. <ul> <li>Once layout occurs, all attached items should be discarded or animated. <li>Attached items are labeled as invalid. <li>Because items may still be prefetched between a "data set completely changed" event and a layout event, all cached items are discarded. </ul>
 * @param dispatchItemsChanged Whether to call{@link LayoutManager#onItemsChanged(RecyclerView)} during measure/layout.
 */
void processDataSetCompletelyChanged(boolean dispatchItemsChanged){
  mDispatchItemsChangedEvent|=dispatchItemsChanged;
  mDataSetHasChangedAfterLayout=true;
  markKnownViewsInvalid();
}
