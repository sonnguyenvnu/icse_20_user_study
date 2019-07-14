/** 
 * Show the given tooltip on the component with the given anchorKey with the specified offsets from the given position.
 * @deprecated
 * @see {{@link #showTooltip(ComponentContext,LithoTooltip,String,int,int)}}
 */
@Deprecated public static void showTooltip(ComponentContext c,DeprecatedLithoTooltip tooltip,String anchorKey,TooltipPosition tooltipPosition,int xOffset,int yOffset){
  final ComponentTree componentTree=c.getComponentTree();
  final Component rootComponent=c.getComponentScope();
  if (componentTree == null) {
    return;
  }
  final String anchorGlobalKey=rootComponent == null ? anchorKey : ComponentKeyUtils.getKeyWithSeparator(rootComponent.getGlobalKey(),anchorKey);
  componentTree.showTooltip(tooltip,anchorGlobalKey,tooltipPosition,xOffset,yOffset);
}
