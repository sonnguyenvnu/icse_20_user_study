public static <T>T[] tail(final T[] elements){
  checkNotNull(elements);
  return Arrays.copyOfRange(elements,1,elements.length);
}
