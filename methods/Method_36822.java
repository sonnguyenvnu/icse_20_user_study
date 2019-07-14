@Nullable @Override public LayoutHelper convertLayoutHelper(LayoutHelper oldHelper){
  ScrollFixLayoutHelper scrollFixHelper;
  if (oldHelper instanceof ScrollFixLayoutHelper) {
    scrollFixHelper=(ScrollFixLayoutHelper)oldHelper;
  }
 else {
    scrollFixHelper=new ScrollFixLayoutHelper(0,0);
  }
  if (mFixStyle != null)   scrollFixHelper.setAspectRatio(mFixStyle.aspectRatio);
  FixCard.FixStyle fixStyle=mFixStyle;
  scrollFixHelper.setAlignType(fixStyle.alignType);
  scrollFixHelper.setShowType(fixStyle.showType);
  scrollFixHelper.setSketchMeasure(fixStyle.sketchMeasure);
  scrollFixHelper.setX(fixStyle.x);
  scrollFixHelper.setY(fixStyle.y);
  return scrollFixHelper;
}
