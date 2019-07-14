/** 
 * Store a  {@link NodeInfo} as a tag in {@code view}.  {@link LithoView} contains the logic forsetting/unsetting it whenever accessibility is enabled/disabled <p>For non  {@link ComponentHost}s this is only done if any  {@link EventHandler}s for accessibility events have been implemented, we want to preserve the original behaviour since {@code view} might have had a default delegate.
 */
private static void setAccessibilityDelegate(View view,NodeInfo nodeInfo){
  if (!(view instanceof ComponentHost) && !nodeInfo.needsAccessibilityDelegate()) {
    return;
  }
  view.setTag(R.id.component_node_info,nodeInfo);
}
