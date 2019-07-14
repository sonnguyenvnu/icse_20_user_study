/** 
 * Stop tracking line changes in all tabs. 
 */
protected void stopTrackingLineChanges(){
  for (  LineID tracked : runtimeLineChanges.values()) {
    tracked.stopTracking();
  }
  runtimeLineChanges.clear();
  runtimeTabsTracked.clear();
}
