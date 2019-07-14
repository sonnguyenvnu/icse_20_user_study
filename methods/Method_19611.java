@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop final String[] images){
  return images.length == 1 ? createImageComponent(c,images[0]).build() : RecyclerCollectionComponent.create(c).disablePTR(true).recyclerConfiguration(LIST_CONFIGURATION).section(DataDiffSection.<String>create(new SectionContext(c)).data(Arrays.asList(images)).renderEventHandler(FeedImageComponent.onRender(c)).build()).canMeasureRecycler(true).aspectRatio(2).build();
}
