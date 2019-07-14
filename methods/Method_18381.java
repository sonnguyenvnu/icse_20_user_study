private void updateMountedContent(MountItem item,LayoutOutput layoutOutput,Component previousComponent){
  final Component newComponent=layoutOutput.getComponent();
  if (isHostSpec(newComponent)) {
    return;
  }
  final Object previousContent=item.getContent();
  previousComponent.unmount(getContextForComponent(previousComponent),previousContent);
  newComponent.mount(getContextForComponent(newComponent),previousContent);
}
