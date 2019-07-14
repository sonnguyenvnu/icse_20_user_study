private static @Nullable Unbinder parseOnEditorAction(final Object target,final Method method,View source){
  OnEditorAction onEditorAction=method.getAnnotation(OnEditorAction.class);
  if (onEditorAction == null) {
    return null;
  }
  validateMember(method);
  final boolean propagateReturn=validateReturnType(method,boolean.class);
  final ArgumentTransformer argumentTransformer=createArgumentTransformer(method,ON_EDITOR_ACTION_TYPES);
  List<TextView> views=findViews(source,onEditorAction.value(),isRequired(method),method.getName(),TextView.class);
  ViewCollections.set(views,ON_EDITOR_ACTION,(v,actionId,event) -> {
    Object value=tryInvoke(method,target,argumentTransformer.transform(v,actionId,event));
    return propagateReturn ? (boolean)value : true;
  }
);
  return new ListenerUnbinder<>(views,ON_EDITOR_ACTION);
}
