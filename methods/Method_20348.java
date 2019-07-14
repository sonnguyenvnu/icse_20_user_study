@Override protected final void buildModels(List<T> list){
  for (  T item : list) {
    add(buildModel(item));
  }
}
