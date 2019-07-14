@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop List<Datum> dataModels){
  return RecyclerCollectionComponent.create(c).disablePTR(true).section(DataDiffSection.<Datum>create(new SectionContext(c)).data(dataModels).renderEventHandler(LithographyRootComponent.onRender(c)).build()).paddingDip(YogaEdge.TOP,8).testKey(MAIN_SCREEN).build();
}
