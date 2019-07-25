public Iterator<String> iterator(){
  return Collections.unmodifiableCollection(data).iterator();
}
