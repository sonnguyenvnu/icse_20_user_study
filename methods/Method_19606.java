@Override public RenderInfo createComponent(ComponentContext c){
  return ComponentRenderInfo.create().component(FeedItemCard.create(c).artist(this).build()).build();
}
