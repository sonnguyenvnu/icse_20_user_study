public ArrayList<Broadcast> toBroadcastList(){
  List<TimelineItem> allItems=CollectionUtils.union(topItems,CollectionUtils.union(hotItems,items));
  return Functional.filter(Functional.map(allItems,TimelineItem::toBroadcast),ObjectsCompat::nonNull);
}
