/** 
 * Sets the non-allocatable bandwidth, which shouldn't be considered available. <p>This method is experimental, and will be renamed or removed in a future release.
 * @param nonAllocatableBandwidth The non-allocatable bandwidth in bits per second.
 */
public void experimental_setNonAllocatableBandwidth(long nonAllocatableBandwidth){
  ((DefaultBandwidthProvider)bandwidthProvider).experimental_setNonAllocatableBandwidth(nonAllocatableBandwidth);
}
