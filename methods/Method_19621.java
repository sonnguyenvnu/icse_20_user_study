@OnCreateInitialState static void createInitialState(ComponentContext c,StateValue<ImmutableList<Pair<String,Integer>>> items,StateValue<Integer> prependCounter,StateValue<Integer> appendCounter){
  final List<Pair<String,Integer>> initialItems=new ArrayList<>();
  initialItems.add(new Pair<>("Coral",0xFFFF7F50));
  initialItems.add(new Pair<>("Ivory",0xFFFFFFF0));
  initialItems.add(new Pair<>("PeachPuff",0xFFFFDAB9));
  initialItems.add(new Pair<>("LightPink",0xFFFFB6C1));
  initialItems.add(new Pair<>("LavenderBlush",0xFFFFF0F5));
  initialItems.add(new Pair<>("Gold",0xFFFFD700));
  initialItems.add(new Pair<>("BlanchedAlmond",0xFFFFEBCD));
  initialItems.add(new Pair<>("FloralWhite",0xFFFFFAF0));
  initialItems.add(new Pair<>("Moccasin",0xFFFFE4B5));
  initialItems.add(new Pair<>("LightYellow",0xFFFFFFE0));
  items.set(new ImmutableList.Builder<Pair<String,Integer>>().addAll(initialItems).build());
  prependCounter.set(0);
  appendCounter.set(0);
}
