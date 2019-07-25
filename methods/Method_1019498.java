/** 
 * Resets this sketch to the empty state, but retains the original value of k.
 */
public void reset(){
  final int ceilingLgK=Util.toLog2(Util.ceilingPowerOf2(reservoirSize_),"ReservoirItemsSketch");
  final int initialLgSize=SamplingUtil.startingSubMultiple(ceilingLgK,rf_.lg(),MIN_LG_ARR_ITEMS);
  currItemsAlloc_=SamplingUtil.getAdjustedSize(reservoirSize_,1 << initialLgSize);
  data_=new ArrayList<>(currItemsAlloc_);
  itemsSeen_=0;
}
