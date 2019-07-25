@Override public Iterator<Generator<?>> iterator(){
  List<Generator<?>> generators=new ArrayList<>();
  for (  Generator<?> each : loader)   generators.add(each);
  Collections.sort(generators,comparing(generator -> generator.getClass().getName()));
  return unmodifiableList(generators).iterator();
}
