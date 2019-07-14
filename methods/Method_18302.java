/** 
 * Show the given tooltip on the component with the given anchorKey.
 * @param c
 * @param lithoTooltip A {@link LithoTooltip} implementation to be shown on the anchor.
 * @param anchorKey key of the Litho Component that will be used as anchor. If unset, the rootcomponent will be used as the anchor.
 * @param xOffset horizontal offset from default position where the tooltip shows.
 * @param yOffset vertical offset from default position where the tooltip shows.
 */
public static void showTooltip(ComponentContext c,LithoTooltip lithoTooltip,@Nullable String anchorKey,int xOffset,int yOffset){
  final ComponentTree componentTree=c.getComponentTree();
  final Component rootComponent=c.getComponentScope();
  if (componentTree == null || componentTree.isReleased() || !componentTree.hasMounted()) {
    return;
  }
  final String anchorGlobalKey;
  if (rootComponent == null && anchorKey == null) {
    return;
  }
 else   if (rootComponent == null) {
    anchorGlobalKey=anchorKey;
  }
 else   if (anchorKey == null) {
    anchorGlobalKey=rootComponent.getGlobalKey();
  }
 else {
    anchorGlobalKey=ComponentKeyUtils.getKeyWithSeparator(rootComponent.getGlobalKey(),anchorKey);
  }
  componentTree.showTooltip(lithoTooltip,anchorGlobalKey,xOffset,yOffset);
}
