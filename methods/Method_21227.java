public <T>void insertSection(final int location,final @NonNull List<T> section){
  this.sections.add(location,new ArrayList<>(section));
}
