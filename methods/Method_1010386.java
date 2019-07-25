public static <T>List<T> subtract(Collection<T> fromCollection,Collection<T> collection){
  ArrayList<T> result=new ArrayList<>();
  for (  T t : fromCollection) {
    if (!collection.contains(t)) {
      result.add(t);
    }
  }
  return result;
}
