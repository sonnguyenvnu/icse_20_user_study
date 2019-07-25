public Collection<Object> values(){
synchronized (this) {
    return Collections.unmodifiableCollection(new ArrayList<>(properties.values()));
  }
}
