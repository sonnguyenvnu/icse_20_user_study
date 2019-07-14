@OnEvent(RenderEvent.class) static RenderInfo onRender(ComponentContext c,@FromEvent ListRow model){
  return ComponentRenderInfo.create().component(model.createComponent(c)).build();
}
