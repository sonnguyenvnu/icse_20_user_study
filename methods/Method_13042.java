public <T>Collection<T> load(Class<T> service){
  ArrayList<T> list=new ArrayList<>();
  Iterator<T> iterator=ServiceLoader.load(service,extensionClassLoader).iterator();
  while (iterator.hasNext()) {
    list.add(iterator.next());
  }
  return list;
}
