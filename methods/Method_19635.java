@OnEvent(RenderEvent.class) static RenderInfo renderStory(SectionContext c,@FromEvent String model,@FromEvent int index){
  return ComponentRenderInfo.create().component(StoryCardComponent.create(c).content(model).header(StoryHeaderComponent.create(c).title("Story #" + index).subtitle("subtitle").build()).build()).build();
}
