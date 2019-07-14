private static @Nullable Unbinder parseOnTouch(final Object target,final Method method,View source){
  OnTouch onTouch=method.getAnnotation(OnTouch.class);
  if (onTouch == null) {
    return null;
  }
  validateMember(method);
  final boolean propagateReturn=validateReturnType(method,boolean.class);
  final ArgumentTransformer argumentTransformer=createArgumentTransformer(method,ON_TOUCH_TYPES);
  List<View> views=findViews(source,onTouch.value(),isRequired(method),method.getName(),View.class);
  ViewCollections.set(views,ON_TOUCH,(v,event) -> {
    Object returnValue=tryInvoke(method,target,argumentTransformer.transform(v));
    return propagateReturn ? (boolean)returnValue : true;
  }
);
  return new ListenerUnbinder<>(views,ON_TOUCH);
}
