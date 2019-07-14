@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop List<ListRow> dataModels){
  return RecyclerCollectionComponent.create(c).disablePTR(true).section(DataDiffSection.<ListRow>create(new SectionContext(c)).data(dataModels).renderEventHandler(ErrorRootComponent.onRender(c)).build()).paddingDip(YogaEdge.TOP,8).build();
}
