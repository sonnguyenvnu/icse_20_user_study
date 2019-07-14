void onBindComponentToContent(Component component,Object content){
  final boolean hasCommonDynamicPropsToBind=hasCommonDynamicPropsToBind(component);
  final boolean hasCustomDynamicProps=component.getDynamicProps().length > 0;
  if (!hasCommonDynamicPropsToBind && !hasCustomDynamicProps) {
    return;
  }
  final Set<DynamicValue<?>> dynamicValues=new HashSet<>();
  if (hasCommonDynamicPropsToBind) {
    final SparseArray<DynamicValue<?>> commonDynamicProps=component.getCommonDynamicProps();
    for (int i=0; i < commonDynamicProps.size(); i++) {
      final int key=commonDynamicProps.keyAt(i);
      final DynamicValue<?> value=commonDynamicProps.valueAt(i);
      bindCommonDynamicProp(key,value,(View)content);
      addDependentComponentAndSubscribeIfNeeded(value,component);
      dynamicValues.add(value);
    }
  }
  final DynamicValue[] dynamicProps=component.getDynamicProps();
  for (int i=0; i < dynamicProps.length; i++) {
    final DynamicValue<?> value=dynamicProps[i];
    component.bindDynamicProp(i,value.get(),content);
    addDependentComponentAndSubscribeIfNeeded(value,component);
    dynamicValues.add(value);
  }
  mAffectingDynamicValues.put(component,dynamicValues);
  mContents.put(component,content);
}
