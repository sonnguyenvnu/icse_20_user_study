@Override public Collection<State> values(){
  Set<State> values=new HashSet<>();
  for (  Item item : itemRegistry.getAll()) {
    values.add(item.getState());
  }
  return values;
}
