@OnEvent(RenderEvent.class) static RenderInfo onRender(final SectionContext c,@FromEvent Integer model){
  if (model.intValue() == 1) {
    return ViewRenderInfo.create().viewBinder(new SimpleViewBinder<TextView>(){
      @Override public void bind(      TextView textView){
        textView.setText("I'm a view in a Litho world");
      }
    }
).viewCreator(VIEW_CREATOR).build();
  }
  return ComponentRenderInfo.create().component(ListItem.create(c).color(model % 2 == 0 ? Color.WHITE : Color.LTGRAY).title(model + ". Hello, world!").subtitle("Litho tutorial").build()).build();
}
