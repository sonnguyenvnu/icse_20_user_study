@OnEvent(RenderEvent.class) static RenderInfo onRender(ComponentContext c,@FromEvent Datum model){
  return model.createComponent(c);
}
