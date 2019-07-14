InternalNode newLayoutBuilder(Component component,@AttrRes int defStyleAttr,@StyleRes int defStyleRes){
  final InternalNode layoutCreatedInWillRender=component.consumeLayoutCreatedInWillRender();
  if (layoutCreatedInWillRender != null) {
    return layoutCreatedInWillRender;
  }
  component=component.getThreadSafeInstance();
  component.updateInternalChildState(this);
  if (ComponentsConfiguration.isDebugModeEnabled) {
    DebugComponent.applyOverrides(this,component);
  }
  final InternalNode node=component.createLayout(component.getScopedContext(),false);
  if (node != NULL_LAYOUT) {
    applyStyle(node,defStyleAttr,defStyleRes);
  }
  return node;
}
