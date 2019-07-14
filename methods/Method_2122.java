@OnCreateChildren static Children onCreateChildren(final SectionContext c){
  return Children.create().child(DataDiffSection.<Data>create(c).data(generateData(c,100)).renderEventHandler(SimpleListSection.onRender(c))).build();
}
