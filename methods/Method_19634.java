@OnCreateChildren static Children onCreateChildren(SectionContext c){
  final Children.Builder builder=Children.create();
  for (char letter='a'; letter <= 'z'; letter++) {
    builder.child(SingleComponentSection.create(c).key("header" + letter).component(Text.create(c).text("Header " + letter).textSizeDip(20).build()).build()).child(DataDiffSection.create(c).data(getStoriesContent()).key("dds" + letter).renderEventHandler(StoryCardsWithHeaderSection.renderStory(c)).build());
  }
  return builder.build();
}
