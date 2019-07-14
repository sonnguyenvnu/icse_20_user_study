@OnEvent(RenderEvent.class) static RenderInfo onRender(ComponentContext c,@FromEvent String model){
  return ComponentRenderInfo.create().component(createImageComponent(c,model).build()).build();
}
