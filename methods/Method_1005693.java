public static <T>TypeId<T> get(Class<T> type){
  if (type.isPrimitive()) {
    @SuppressWarnings("unchecked") TypeId<T> result=(TypeId<T>)PRIMITIVE_TO_TYPE.get(type);
    return result;
  }
  String name=type.getName().replace('.','/');
  return get(type.isArray() ? name : 'L' + name + ';');
}
