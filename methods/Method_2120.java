@OnEvent(RenderEvent.class) static RenderInfo onRender(final SectionContext c,@FromEvent Uri model){
  return ComponentRenderInfo.create().component(FrescoVitoImage.create(c).uri(model).imageOptions(IMAGE_OPTIONS).paddingDip(YogaEdge.ALL,2)).build();
}
