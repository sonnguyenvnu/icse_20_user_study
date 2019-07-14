@Nullable @Override public LayoutHelper convertLayoutHelper(@Nullable LayoutHelper oldHelper){
  ScrollFixLayoutHelper scrollFixHelper;
  if (oldHelper instanceof ScrollFixLayoutHelper) {
    scrollFixHelper=(ScrollFixLayoutHelper)oldHelper;
  }
 else {
    scrollFixHelper=new ScrollFixLayoutHelper(0,0);
  }
  if (style != null)   scrollFixHelper.setAspectRatio(style.aspectRatio);
  if (style instanceof FixStyle) {
    FixStyle fixStyle=(FixStyle)style;
    scrollFixHelper.setAlignType(fixStyle.alignType);
    scrollFixHelper.setShowType(fixStyle.showType);
    scrollFixHelper.setSketchMeasure(fixStyle.sketchMeasure);
    scrollFixHelper.setX(fixStyle.x);
    scrollFixHelper.setY(fixStyle.y);
  }
 else {
    scrollFixHelper.setAlignType(FixLayoutHelper.TOP_LEFT);
    scrollFixHelper.setShowType(ScrollFixLayoutHelper.SHOW_ALWAYS);
    scrollFixHelper.setSketchMeasure(true);
    scrollFixHelper.setX(0);
    scrollFixHelper.setY(0);
  }
  return scrollFixHelper;
}
