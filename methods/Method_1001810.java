public boolean grouping(String title,String comment,GroupingType type,HtmlColor backColorGeneral,HtmlColor backColorElement,boolean parallel){
  if (type != GroupingType.START && openGroupings.size() == 0) {
    return false;
  }
  if (backColorGeneral == null) {
    backColorGeneral=getSkinParam().getHtmlColor(ColorParam.sequenceGroupBodyBackground,null,false);
  }
  final GroupingStart top=openGroupings.size() > 0 ? openGroupings.get(0) : null;
  final Grouping g=type == GroupingType.START ? new GroupingStart(title,comment,backColorGeneral,backColorElement,top) : new GroupingLeaf(title,comment,type,backColorGeneral,backColorElement,top);
  events.add(g);
  if (type == GroupingType.START) {
    if (parallel) {
      ((GroupingStart)g).goParallel();
    }
    openGroupings.add(0,(GroupingStart)g);
  }
 else   if (type == GroupingType.END) {
    openGroupings.remove(0);
    lastEventWithDeactivate=(GroupingLeaf)g;
  }
  return true;
}
