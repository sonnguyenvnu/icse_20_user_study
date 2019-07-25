private static <V>V coalesce(V... values){
  for (  V value : values) {
    if (value != null) {
      return value;
    }
  }
  return null;
}
