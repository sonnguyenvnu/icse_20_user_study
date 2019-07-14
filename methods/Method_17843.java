InternalNode resolveLayout(Component component){
  final InternalNode layoutCreatedInWillRender=component.consumeLayoutCreatedInWillRender();
  if (layoutCreatedInWillRender != null) {
    return layoutCreatedInWillRender;
  }
  component=component.getThreadSafeInstance();
  component.updateInternalChildState(this);
  if (ComponentsConfiguration.isDebugModeEnabled) {
    DebugComponent.applyOverrides(this,component);
  }
  final InternalNode node=(InternalNode)component.resolve(component.getScopedContext());
  if (component.canResolve()) {
    final CommonPropsCopyable props=component.getCommonPropsCopyable();
    if (props != null) {
      props.copyInto(component.getScopedContext(),node);
    }
  }
  return node;
}
