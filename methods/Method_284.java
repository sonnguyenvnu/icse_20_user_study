private static @Nullable Unbinder parseOnLongClick(final Object target,final Method method,View source){
  OnLongClick onLongClick=method.getAnnotation(OnLongClick.class);
  if (onLongClick == null) {
    return null;
  }
  validateMember(method);
  final boolean propagateReturn=validateReturnType(method,boolean.class);
  final ArgumentTransformer argumentTransformer=createArgumentTransformer(method,ON_LONG_CLICK_TYPES);
  List<View> views=findViews(source,onLongClick.value(),isRequired(method),method.getName(),View.class);
  ViewCollections.set(views,ON_LONG_CLICK,v -> {
    Object returnValue=tryInvoke(method,target,argumentTransformer.transform(v));
    return propagateReturn ? (boolean)returnValue : true;
  }
);
  return new ListenerUnbinder<>(views,ON_LONG_CLICK);
}
