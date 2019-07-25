@NotNull private Class<?> box(Class<?> type){
  if (!type.isPrimitive()) {
    return type;
  }
  return BoxingHelper.box(type);
}
