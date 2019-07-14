/** 
 * Set a DP value to use as the padding on each side of the carousel and in between carousel items. <p>The default as the value returned by  {@link #getDefaultSpacingBetweenItemsDp()}
 */
@ModelProp(defaultValue="NO_VALUE_SET",group="padding") public void setPaddingDp(@Dimension(unit=Dimension.DP) int paddingDp){
  int px=dpToPx(paddingDp != NO_VALUE_SET ? paddingDp : getDefaultSpacingBetweenItemsDp());
  setPadding(px,px,px,px);
  setItemSpacingPx(px);
}
