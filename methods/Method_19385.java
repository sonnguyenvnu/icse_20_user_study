@OnCreateLayout static Component onCreateLayout(ComponentContext c,@State String selection,@State boolean isShowingDropDown,@Prop(resType=ResType.DIMEN_TEXT,optional=true) float selectedTextSize,@Prop(resType=ResType.COLOR,optional=true) int selectedTextColor,@Prop(resType=ResType.DRAWABLE,optional=true) @Nullable Drawable caret){
  assertAPI11orHigher();
  caret=caret == null ? new CaretDrawable(c.getAndroidContext(),DEFAULT_CARET_COLOR) : caret;
  selectedTextSize=selectedTextSize == -1 ? spToPx(c.getAndroidContext(),DEFAULT_TEXT_SIZE_SP) : selectedTextSize;
  return Row.create(c).minHeightDip(SPINNER_HEIGHT).justifyContent(YogaJustify.SPACE_BETWEEN).paddingDip(START,MARGIN_SMALL).backgroundAttr(android.R.attr.selectableItemBackground).clickHandler(Spinner.onClick(c)).child(createSelectedItemText(c,selection,(int)selectedTextSize,selectedTextColor)).child(createCaret(c,caret,isShowingDropDown)).accessibilityRole(AccessibilityRole.DROP_DOWN_LIST).build();
}
