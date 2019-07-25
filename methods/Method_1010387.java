public static <T>boolean intersects(Collection<T> collection1,Collection<T> collection2){
  if (collection2.isEmpty() || collection1.isEmpty()) {
    return false;
  }
  for (  T t : collection1) {
    if (collection2.contains(t)) {
      return true;
    }
  }
  return false;
}
