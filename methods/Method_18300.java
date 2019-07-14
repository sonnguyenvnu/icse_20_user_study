/** 
 * Show the given tooltip with the specified offsets from the bottom-left corner of the root component.
 */
public static void showTooltipOnRootComponent(ComponentContext c,final PopupWindow popupWindow,int xOffset,int yOffset){
  showTooltip(c,popupWindow,null,xOffset,yOffset);
}
