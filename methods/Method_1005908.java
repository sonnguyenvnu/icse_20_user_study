@NonNull @Override public List<ViewAttribute> collect(CollapsingToolbarLayout view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new ViewAttribute<>("Title",view.getTitle()));
  attributes.add(new ViewAttribute<>("TitleEnabled",view.isTitleEnabled()));
  attributes.add(new ViewAttribute<>("TitleGravityCollapsed",new GravityValue(view.getCollapsedTitleGravity())));
  attributes.add(new ViewAttribute<>("TitleTypefaceCollapsed",new TypefaceValue(view.getCollapsedTitleTypeface())));
  attributes.add(new ViewAttribute<>("TitleGravityExpanded",new GravityValue(view.getExpandedTitleGravity())));
  attributes.add(new ViewAttribute<>("TitleTypefaceExpanded",new TypefaceValue(view.getExpandedTitleTypeface())));
  attributes.add(new ViewAttribute<>("TitleMarginStartExpanded",attributeTranslator.translatePx(view.getExpandedTitleMarginStart())));
  attributes.add(new ViewAttribute<>("TitleMarginTopExpanded",attributeTranslator.translatePx(view.getExpandedTitleMarginTop())));
  attributes.add(new ViewAttribute<>("TitleMarginEndExpanded",attributeTranslator.translatePx(view.getExpandedTitleMarginEnd())));
  attributes.add(new ViewAttribute<>("TitleMarginBottomExpanded",attributeTranslator.translatePx(view.getExpandedTitleMarginBottom())));
  attributes.add(new ViewAttribute<>("ScrimVisibleHeightTrigger",view.getScrimVisibleHeightTrigger()));
  attributes.add(new ViewAttribute<>("ScrimAnimationDuration",view.getScrimAnimationDuration()));
  attributes.add(new ViewAttribute<Void>("ContentScrim",view.getContentScrim()));
  attributes.add(new ViewAttribute<Void>("StatusBarScrim",view.getStatusBarScrim()));
  return attributes;
}
