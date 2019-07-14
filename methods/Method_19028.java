@OnDiff public static void onCreateChangeSet(SectionContext context,ChangeSet changeSet,@Prop Diff<Component> component,@Prop(optional=true) Diff<Boolean> sticky,@Prop(optional=true) Diff<Integer> spanSize,@Prop(optional=true) Diff<Boolean> isFullSpan,@Prop(optional=true) Diff<Map<String,Object>> customAttributes,@Prop(optional=true) Diff<Object> data){
  final Object prevData=data.getPrevious();
  final Object nextData=data.getNext();
  if (component.getNext() == null) {
    changeSet.delete(0,prevData);
    return;
  }
  boolean isNextSticky=false;
  if (sticky != null && sticky.getNext() != null) {
    isNextSticky=sticky.getNext();
  }
  int nextSpanSize=1;
  if (spanSize != null && spanSize.getNext() != null) {
    nextSpanSize=spanSize.getNext();
  }
  boolean isNextFullSpan=false;
  if (isFullSpan != null && isFullSpan.getNext() != null) {
    isNextFullSpan=isFullSpan.getNext();
  }
  if (component.getPrevious() == null) {
    changeSet.insert(0,addCustomAttributes(ComponentRenderInfo.create(),customAttributes.getNext(),context).component(component.getNext()).isSticky(isNextSticky).spanSize(nextSpanSize).isFullSpan(isNextFullSpan).build(),context.getTreePropsCopy(),nextData);
    return;
  }
  boolean isPrevSticky=false;
  if (sticky != null && sticky.getPrevious() != null) {
    isPrevSticky=sticky.getPrevious();
  }
  int prevSpanSize=1;
  if (spanSize != null && spanSize.getPrevious() != null) {
    prevSpanSize=spanSize.getPrevious();
  }
  boolean isPrevFullSpan=false;
  if (isFullSpan != null && isFullSpan.getPrevious() != null) {
    isPrevFullSpan=isFullSpan.getPrevious();
  }
  final boolean customAttributesEqual=MapDiffUtils.areMapsEqual(customAttributes.getPrevious(),customAttributes.getNext());
  if (isPrevSticky != isNextSticky || prevSpanSize != nextSpanSize || isPrevFullSpan != isNextFullSpan || !component.getPrevious().isEquivalentTo(component.getNext()) || !customAttributesEqual) {
    changeSet.update(0,addCustomAttributes(ComponentRenderInfo.create(),customAttributes.getNext(),context).component(component.getNext()).isSticky(isNextSticky).spanSize(nextSpanSize).isFullSpan(isNextFullSpan).build(),context.getTreePropsCopy(),prevData,nextData);
  }
}
