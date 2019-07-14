@OnEvent(RenderEvent.class) static RenderInfo onRender(final SectionContext c,@FromEvent Data model){
  return ComponentRenderInfo.create().component(SimpleListItem.create(c).profilePicture(model.profilePicture).mainPicture(model.mainPicture).title(model.title)).build();
}
