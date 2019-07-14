@Override public RenderInfo createComponent(ComponentContext c){
  return ComponentRenderInfo.create().component(DecadeSeparator.create(c).decade(this).build()).isSticky(true).build();
}
