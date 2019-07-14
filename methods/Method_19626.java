@OnCreateChildren static Children onCreateChildren(final SectionContext c){
  return Children.create().child(SingleComponentSection.create(c).component(RecyclerCollectionComponent.create(c).disablePTR(true).recyclerConfiguration(new ListRecyclerConfiguration(LinearLayoutManager.HORIZONTAL,false,SNAP_TO_CENTER)).section(DataDiffSection.<Integer>create(c).data(generateData(32)).renderEventHandler(ListSection.onRender(c)).build()).canMeasureRecycler(true)).build()).child(DataDiffSection.<Integer>create(c).data(generateData(32)).renderEventHandler(ListSection.onRender(c))).build();
}
