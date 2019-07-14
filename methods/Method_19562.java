@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return RecyclerCollectionComponent.create(c).disablePTR(true).section(DataDiffSection.<Class<? extends Component>>create(new SectionContext(c)).data(componentsToBuild).renderEventHandler(BorderEffectsComponent.onRender(c)).build()).build();
}
