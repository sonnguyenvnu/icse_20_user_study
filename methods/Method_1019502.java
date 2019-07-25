/** 
 * Union a reservoir sketch. The reservoir sample is treated as if all items were added with a weight of 1.0.
 * @param reservoirIn The reservoir sketch to be merged
 */
public void update(final ReservoirItemsSketch<T> reservoirIn){
  if (reservoirIn != null) {
    mergeReservoirInto(reservoirIn);
  }
}
