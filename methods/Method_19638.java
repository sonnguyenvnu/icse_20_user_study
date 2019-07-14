@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  final RecyclerBinder recyclerBinder=new RecyclerBinder.Builder().layoutInfo(new LinearLayoutInfo(c,OrientationHelper.VERTICAL,false)).build(c);
  Populator.with(recyclerBinder,c).addRow("Layout Specs",ExamplesActivityComponent.onClickLayoutSpecs(c)).addRow("Text Widget",ExamplesActivityComponent.onClickTextWidget(c)).addRow("Containers",ExamplesActivityComponent.onClickContainers(c)).addRow("Props",ExamplesActivityComponent.onClickProps(c)).addRow("Layout Props",ExamplesActivityComponent.onClickLayoutProps(c)).addRow("Click Events",ExamplesActivityComponent.onClickClickEvents(c)).addRow("State",ExamplesActivityComponent.onClickState(c)).addRow("Recycler Binder",ExamplesActivityComponent.onClickRecyclerBinder(c));
  return Recycler.create(c).binder(recyclerBinder).build();
}
