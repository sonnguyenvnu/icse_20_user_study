private static Component createHorizontalScrollChildren(ComponentContext c,List<Pair<String,Integer>> items){
  final Row.Builder rowBuilder=Row.create(c);
  for (  Pair<String,Integer> colorItem : items) {
    rowBuilder.child(Row.create(c).paddingDip(YogaEdge.ALL,10).backgroundColor(colorItem.second).child(Text.create(c).text(colorItem.first).textSizeSp(20).alignSelf(YogaAlign.CENTER).heightDip(100)));
  }
  return rowBuilder.build();
}
