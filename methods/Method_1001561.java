public Iterator<CharSequence> iterator(){
  return Collections.unmodifiableList(displayData).iterator();
}
