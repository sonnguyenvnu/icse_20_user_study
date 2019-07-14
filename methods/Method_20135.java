/** 
 * Set a dimension resource to specify the padding value to use on each side of the carousel and in between carousel items.
 */
@ModelProp(group="padding") public void setPaddingRes(@DimenRes int paddingRes){
  int px=resToPx(paddingRes);
  setPadding(px,px,px,px);
  setItemSpacingPx(px);
}
