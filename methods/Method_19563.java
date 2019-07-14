@OnEvent(RenderEvent.class) static RenderInfo onRender(ComponentContext c,@FromEvent Class<? extends Component> model){
  try {
    Method createMethod=model.getMethod("create",ComponentContext.class);
    Component.Builder componentBuilder=(Component.Builder)createMethod.invoke(null,c);
    return ComponentRenderInfo.create().component(componentBuilder.build()).build();
  }
 catch (  NoSuchMethodException|IllegalAccessException|IllegalArgumentException|InvocationTargetException ex) {
    return ComponentRenderInfo.create().component(Text.create(c).textSizeDip(32).text(ex.getLocalizedMessage()).build()).build();
  }
}
