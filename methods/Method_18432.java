private void unbindAndUnmountLifecycle(MountItem item){
  final Component component=item.getComponent();
  final Object content=item.getContent();
  final ComponentContext context=getContextForComponent(component);
  if (item.isBound()) {
    component.onUnbind(context,content);
    item.setIsBound(false);
  }
  component.unmount(context,content);
}
