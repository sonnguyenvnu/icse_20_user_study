@Override public Void log(List<T> entries){
  for (  T entry : entries)   log(entry);
  return null;
}
