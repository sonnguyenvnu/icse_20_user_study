public <T>void setSection(final int location,final @NonNull List<T> section){
  this.sections.set(location,new ArrayList<>(section));
}
