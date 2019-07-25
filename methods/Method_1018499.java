public static <T>Stream<T> stream(T[] array){
  return stream(array,0,array.length);
}
