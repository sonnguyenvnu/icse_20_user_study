public static <T>T head(final T[] elements){
  checkNotNull(elements);
  if (elements.length == 0) {
    return null;
  }
  return elements[0];
}
