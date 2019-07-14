@OnEvent(RenderEvent.class) static RenderInfo onRender(ComponentContext c,@Prop @Nullable int[] parentIndices,@FromEvent DemoListActivity.DemoListDataModel model,@FromEvent int index){
  return ComponentRenderInfo.create().component(DemoListItemComponent.create(c).model(model).currentIndices(getUpdatedIndices(parentIndices,index)).build()).build();
}
