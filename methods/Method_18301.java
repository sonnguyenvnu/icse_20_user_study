/** 
 * Show the given tooltip on the component with the given anchorKey.
 * @param c
 * @param lithoTooltip A {@link LithoTooltip} implementation to be shown on the anchor.
 * @param anchorKey key of the Litho Component that will be used as anchor. If unset, the rootcomponent will be used as the anchor.
 */
public static void showTooltip(ComponentContext c,LithoTooltip lithoTooltip,@Nullable String anchorKey){
  showTooltip(c,lithoTooltip,anchorKey,0,0);
}
