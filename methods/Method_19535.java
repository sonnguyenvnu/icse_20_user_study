@OnEvent(RenderEvent.class) static RenderInfo onRender(ComponentContext c,@FromEvent Message model){
  return model.createComponent(c);
}
