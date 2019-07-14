private static <T>T[] array2Array(Object[] src,Class<T> componentType){
  Object array=Array.newInstance(componentType,src.length);
  for (int i=0; i < src.length; ++i) {
    Array.set(array,i,deserializeByType(src[i],componentType));
  }
  return (T[])array;
}
