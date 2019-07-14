void onUnbindComponent(Component component){
  if (!hasCommonDynamicPropsToBind(component) && component.getDynamicProps().length == 0) {
    return;
  }
  mContents.remove(component);
  final Set<DynamicValue<?>> dynamicValues=mAffectingDynamicValues.get(component);
  if (dynamicValues == null) {
    return;
  }
  for (  DynamicValue<?> value : dynamicValues) {
    removeDependentComponentAndUnsubscribeIfNeeded(value,component);
  }
}
