@Override protected T[] format(Object payload){
  if (!payload.getClass().isArray()) {
    throw new XPipeProxyResultException(getClass().getSimpleName() + ": result should be an array");
  }
  Object[] objects=(Object[])payload;
  T[] result=initArray(objects);
  int index=0;
  for (  Object object : objects) {
    result[index++]=parseObject(object);
  }
  return result;
}
