@OnEvent(ClickEvent.class) static void onClick(ComponentContext c,@State Boolean expanded){
  final boolean isExpanded=expanded == null ? false : expanded;
  ExpandableElementMe.updateExpandedStateSync(c,!isExpanded);
}
