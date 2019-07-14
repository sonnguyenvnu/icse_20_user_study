/** 
 * Called on parent onStart. Use for any kind of refresh activities.
 */
public void refresh(){
  timelineEntry.setVisibility(isDebug() && Prefs.timelineEnabled() ? VISIBLE : GONE);
}
