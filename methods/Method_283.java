private static @Nullable Unbinder parseOnFocusChange(final Object target,final Method method,View source){
  OnFocusChange onFocusChange=method.getAnnotation(OnFocusChange.class);
  if (onFocusChange == null) {
    return null;
  }
  validateMember(method);
  validateReturnType(method,void.class);
  final ArgumentTransformer argumentTransformer=createArgumentTransformer(method,ON_FOCUS_CHANGE_TYPES);
  List<View> views=findViews(source,onFocusChange.value(),isRequired(method),method.getName(),View.class);
  ViewCollections.set(views,ON_FOCUS_CHANGE,(v,hasFocus) -> tryInvoke(method,target,argumentTransformer.transform(v,hasFocus)));
  return new ListenerUnbinder<>(views,ON_FOCUS_CHANGE);
}
