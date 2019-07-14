@Nullable @Override public LayoutHelper convertLayoutHelper(@Nullable LayoutHelper oldHelper){
  FloatLayoutHelper helper;
  if (oldHelper instanceof FloatLayoutHelper) {
    helper=(FloatLayoutHelper)oldHelper;
  }
 else {
    helper=new FloatLayoutHelper();
  }
  helper.setItemCount(mCells.size());
  if (style instanceof FixStyle) {
    FixStyle fixStyle=(FixStyle)style;
    helper.setAlignType(fixStyle.alignType);
    helper.setDefaultLocation(fixStyle.x,fixStyle.y);
  }
  return helper;
}
