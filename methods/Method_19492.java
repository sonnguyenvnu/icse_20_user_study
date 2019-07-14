@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return RecyclerCollectionComponent.create(c).disablePTR(true).section(DataDiffSection.create(new SectionContext(c)).data(generateData(20)).renderEventHandler(ComposedAnimationsComponent.onRender(c)).onCheckIsSameItemEventHandler(ComposedAnimationsComponent.isSameItem(c)).build()).build();
}
