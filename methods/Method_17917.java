/** 
 * Create a layout from the given component.
 * @param context ComponentContext associated with the current ComponentTree.
 * @param resolveNestedTree if the component's layout tree should be resolved as part of thiscall.
 * @return New InternalNode associated with the given component.
 */
InternalNode createLayout(ComponentContext context,boolean resolveNestedTree){
  final Component component=(Component)this;
  final InternalNode layoutCreatedInWillRender=component.consumeLayoutCreatedInWillRender();
  if (layoutCreatedInWillRender != null) {
    return layoutCreatedInWillRender;
  }
  final boolean deferNestedTreeResolution=Component.isNestedTree((Component)this) && !resolveNestedTree;
  final TreeProps parentTreeProps=context.getTreeProps();
  context.setTreeProps(getTreePropsForChildren(context,parentTreeProps));
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    ComponentsSystrace.beginSection("createLayout:" + ((Component)this).getSimpleName());
  }
  InternalNode node;
  try {
    if (deferNestedTreeResolution) {
      node=InternalNodeUtils.create(context);
      node.markIsNestedTreeHolder(context.getTreeProps());
    }
 else     if (component.canResolve()) {
      context.setTreeProps(component.getScopedContext().getTreePropsCopy());
      node=(InternalNode)component.resolve(context);
    }
 else {
      final Component layoutComponent=createComponentLayout(context);
      if (layoutComponent == null || layoutComponent.getId() <= 0) {
        node=null;
      }
 else {
        node=context.resolveLayout(layoutComponent);
      }
    }
  }
 catch (  Throwable t) {
    throw new ComponentsChainException((Component)this,t);
  }
  if (isTracing) {
    ComponentsSystrace.endSection();
  }
  if (node == null || node == ComponentContext.NULL_LAYOUT) {
    return ComponentContext.NULL_LAYOUT;
  }
  final CommonPropsCopyable commonProps=((Component)this).getCommonPropsCopyable();
  if (commonProps != null && (deferNestedTreeResolution || !Component.isLayoutSpecWithSizeSpec((Component)this))) {
    commonProps.copyInto(context,node);
  }
  if (node.getTailComponent() == null) {
    final boolean isMountSpecWithMeasure=canMeasure() && Component.isMountSpec((Component)this);
    if (isMountSpecWithMeasure || deferNestedTreeResolution) {
      node.setMeasureFunction(sMeasureFunction);
    }
  }
  node.appendComponent((Component)this);
  if (TransitionUtils.areTransitionsEnabled(context.getAndroidContext())) {
    if (needsPreviousRenderData()) {
      node.addComponentNeedingPreviousRenderData((Component)this);
    }
 else {
      final Transition transition=createTransition(context);
      if (transition != null) {
        node.addTransition(transition);
      }
    }
  }
  if (!deferNestedTreeResolution) {
    onPrepare(context);
  }
  if (component.mWorkingRangeRegistrations != null && !component.mWorkingRangeRegistrations.isEmpty()) {
    node.addWorkingRanges(component.mWorkingRangeRegistrations);
  }
  return node;
}
