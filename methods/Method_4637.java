/** 
 * Returns the  {@link TrackBundle} whose fragment run has the earliest file position out of thoseyet to be consumed, or null if all have been consumed.
 */
private static TrackBundle getNextFragmentRun(SparseArray<TrackBundle> trackBundles){
  TrackBundle nextTrackBundle=null;
  long nextTrackRunOffset=Long.MAX_VALUE;
  int trackBundlesSize=trackBundles.size();
  for (int i=0; i < trackBundlesSize; i++) {
    TrackBundle trackBundle=trackBundles.valueAt(i);
    if (trackBundle.currentTrackRunIndex == trackBundle.fragment.trunCount) {
    }
 else {
      long trunOffset=trackBundle.fragment.trunDataPosition[trackBundle.currentTrackRunIndex];
      if (trunOffset < nextTrackRunOffset) {
        nextTrackBundle=trackBundle;
        nextTrackRunOffset=trunOffset;
      }
    }
  }
  return nextTrackBundle;
}
