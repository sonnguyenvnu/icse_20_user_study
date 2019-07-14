@OnEvent(ClickEvent.class) static void onClick(ComponentContext c,@State(canUpdateLazily=true) int prependCounter,@State(canUpdateLazily=true) int appendCounter,@State ImmutableList<Pair<String,Integer>> items,@Param boolean isPrepend){
  final ArrayList<Pair<String,Integer>> updatedItems=new ArrayList<>(items);
  if (isPrepend) {
    updatedItems.add(0,new Pair<>("Prepend#" + prependCounter,0xFF7CFC00));
    HorizontalScrollRootComponent.lazyUpdatePrependCounter(c,++prependCounter);
  }
 else {
    updatedItems.add(new Pair<>("Append#" + appendCounter,0xFF6495ED));
    HorizontalScrollRootComponent.lazyUpdateAppendCounter(c,++appendCounter);
  }
  HorizontalScrollRootComponent.updateItems(c,new ImmutableList.Builder<Pair<String,Integer>>().addAll(updatedItems).build());
}
