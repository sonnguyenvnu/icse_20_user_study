@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop List<DemoListActivity.DemoListDataModel> dataModels){
  return RecyclerCollectionComponent.create(c).section(DataDiffSection.<DemoListActivity.DemoListDataModel>create(new SectionContext(c)).data(dataModels).renderEventHandler(DemoListComponent.onRender(c)).onCheckIsSameItemEventHandler(DemoListComponent.isSameItem(c)).onCheckIsSameContentEventHandler(DemoListComponent.isSameContent(c)).build()).disablePTR(true).testKey(MAIN_SCREEN).build();
}
