/** 
 * Show the given tooltip on the component with the given anchorKey in the specified position.
 * @deprecated @see {#show}
 */
@Deprecated public static void showTooltip(ComponentContext c,DeprecatedLithoTooltip tooltip,String anchorKey,TooltipPosition tooltipPosition){
  showTooltip(c,tooltip,anchorKey,tooltipPosition,0,0);
}
