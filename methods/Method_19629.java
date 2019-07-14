@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return RecyclerCollectionComponent.create(c).section(StoryCardsWithHeaderSection.create(new SectionContext(c)).build()).build();
}
