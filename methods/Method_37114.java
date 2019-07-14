@Nullable @Override public LayoutHelper convertLayoutHelper(LayoutHelper oldHelper){
  ScrollFixLayoutHelper scrollFixHelper;
  if (oldHelper instanceof ScrollFixLayoutHelper) {
    scrollFixHelper=(ScrollFixLayoutHelper)oldHelper;
  }
 else {
    scrollFixHelper=new ScrollFixLayoutHelper(0,0);
  }
  if (mFixStyle != null) {
    scrollFixHelper.setAspectRatio(mFixStyle.aspectRatio);
    scrollFixHelper.setAlignType(mFixStyle.alignType);
    scrollFixHelper.setShowType(mFixStyle.showType);
    scrollFixHelper.setSketchMeasure(mFixStyle.sketchMeasure);
    scrollFixHelper.setX(mFixStyle.x);
    scrollFixHelper.setY(mFixStyle.y);
  }
  return scrollFixHelper;
}
