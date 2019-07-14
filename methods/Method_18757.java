@OnDataBound static void onDataBound(SectionContext c,@Prop int numberOfDummy,@State(canUpdateLazily=true) int extra){
  VerySimpleGroupSection.lazyUpdateExtra(c,extra - numberOfDummy);
}
