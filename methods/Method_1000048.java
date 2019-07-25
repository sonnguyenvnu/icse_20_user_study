public static <T>List<T> truncate(List<T> items,int limit){
  if (limit > items.size()) {
    return new ArrayList<>(items);
  }
  List<T> truncated=new ArrayList<>(limit);
  for (  T item : items) {
    truncated.add(item);
    if (truncated.size() == limit) {
      break;
    }
  }
  return truncated;
}
